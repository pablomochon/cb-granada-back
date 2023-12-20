package com.basketballticketsproject.basketballticketsproject.service;

import com.basketballticketsproject.basketballticketsproject.entity.Partido;
import com.basketballticketsproject.basketballticketsproject.entity.Sorteo;
import com.basketballticketsproject.basketballticketsproject.entity.Ticket;
import com.basketballticketsproject.basketballticketsproject.repo.PartidoRepo;
import com.basketballticketsproject.basketballticketsproject.repo.SorteoRepo;
import com.basketballticketsproject.basketballticketsproject.repo.TicketRepo;
import com.basketballticketsproject.basketballticketsproject.repo.UsuarioRepo;
import com.basketballticketsproject.basketballticketsproject.utils.EnviarEmailUsuarios;
import com.google.gson.Gson;
import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class FileStorageService {

    @Autowired
    private PartidoRepo partidoRepo;

    @Autowired
    private TicketRepo ticketRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;
    @Autowired
    private SorteoRepo sorteoRepo;


    public int storeFile(MultipartFile multipartFile, String partidoString) throws IOException {

        File convFile = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));

        //splittear el pdf en varios
        PDDocument document = PDDocument.load(convFile);

        Splitter splitter = new Splitter();

        List<PDDocument> pages = splitter.split(document);

        Iterator<PDDocument> iterator = pages.listIterator();

        Partido partido = new Gson().fromJson(partidoString, Partido.class);


        Set<Ticket> ticketSet = new HashSet<>();

        final Sorteo sorteo = new Sorteo();

        //comprobar si no se ha creado ese partido
        Partido byFechaPartido = partidoRepo.findByFechaPartido(partido.getFechaPartido());


        if (ObjectUtils.isEmpty(byFechaPartido)) {
            //recorrer todas las paginas del pdf
            for (int i = 1; iterator.hasNext(); i++) {

                Ticket ticket = new Ticket();
                PDDocument pd = iterator.next();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                pd.save(baos);

                //encodear el pdf en base64
                String base64String = Base64.getEncoder().encodeToString(baos.toByteArray());
                ticket.setPdfBase64(base64String);
                ticket.setEntrada(String.valueOf(i));
                ticketSet.add(ticket);
                ticket.setPartido(partido);
                ticketRepo.save(ticket);


                //guardo las entradas en el partido que corresponda

                pd.close();

            }
            partido.setTickets(ticketSet);
            partidoRepo.save(partido);
            sorteo.setPartido(partido);
            sorteoRepo.save(sorteo);

        }
        document.close();

        return ticketSet.size();

    }

    //obtener pdf de la entrada dado su nombre
    public byte[] getFiles(String fileName) {
        Ticket byEntrada = ticketRepo.findByEntrada(fileName);
        return EnviarEmailUsuarios.decodeBase64ToPdf(byEntrada);
    }

}