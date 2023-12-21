package com.basketballticketsproject.basketballticketsproject.service;

import com.basketballticketsproject.basketballticketsproject.entity.Partido;
import com.basketballticketsproject.basketballticketsproject.entity.Sorteo;
import com.basketballticketsproject.basketballticketsproject.entity.Ticket;
import com.basketballticketsproject.basketballticketsproject.repo.PartidoRepo;
import com.basketballticketsproject.basketballticketsproject.repo.SorteoRepo;
import com.basketballticketsproject.basketballticketsproject.repo.TicketRepo;
import com.basketballticketsproject.basketballticketsproject.repo.UsuarioRepo;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@Service
@Slf4j
public class FileStorageService {

    @Autowired
    private PartidoRepo partidoRepo;

    @Autowired
    private TicketRepo ticketRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;
    @Autowired
    private SorteoRepo sorteoRepo;


    public int storeFile(final File convFile, final String tituloPartido, final String fechaPartido) throws IOException {

        //splittear el pdf en varios
        final PDDocument document = PDDocument.load(convFile);

        final Splitter splitter = new Splitter();

        final List<PDDocument> pages = splitter.split(document);

        final Iterator<PDDocument> iterator = pages.listIterator();

        final Partido partido = new Partido();
        partido.setNombrePartido(tituloPartido);
        partido.setFechaPartido(fechaPartido);

        final Set<Ticket> ticketSet = new HashSet<>();

        final Sorteo sorteo = new Sorteo();

        //comprobar si no se ha creado ese partido
        final Partido byFechaPartido = partidoRepo.findByFechaPartido(partido.getFechaPartido());


        if (ObjectUtils.isEmpty(byFechaPartido)) {
            //recorrer todas las paginas del pdf
            for (int i = 1; iterator.hasNext(); i++) {

                final Ticket ticket = new Ticket();
                final PDDocument pd = iterator.next();
                final ByteArrayOutputStream baos = new ByteArrayOutputStream();
                pd.save(baos);

                //encodear el pdf en base64
                final String base64String = Base64.getEncoder().encodeToString(baos.toByteArray());
                ticket.setPdfBase64(base64String);
                ticket.setEntrada(String.valueOf(i));
                ticketSet.add(ticket);
                ticket.setPartido(partido);
                ticketRepo.save(ticket);

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


    public File getFileBase(final String base64) {
    	final File file = new File("Entradas.pdf");
		try {
			FileOutputStream fos = new FileOutputStream(file);
			String cadenaLimpia = base64.replace("data:application/pdf;base64,", "");
			byte[] decoder = Base64.getDecoder().decode(cadenaLimpia);
			fos.write(decoder);
			fos.flush();
			fos.close();
		} catch (IOException e) {
			log.info("Error al decodificar pdf", e);
		}
		return file;
    }

}