package com.basketballticketsproject.basketballticketsproject.service;

import com.basketballticketsproject.basketballticketsproject.entity.Partido;
import com.basketballticketsproject.basketballticketsproject.entity.Ticket;
import com.basketballticketsproject.basketballticketsproject.repo.PartidoRepo;
import com.basketballticketsproject.basketballticketsproject.repo.TicketRepo;
import com.google.gson.Gson;
import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class SplitPDFByPages {

    @Autowired
    private PartidoRepo partidoRepo;

    @Autowired
    private TicketRepo ticketRepo;


    public int splitPdf(MultipartFile pdfFile, String partidoString) throws IOException {
        File convFile = new File(Objects.requireNonNull(pdfFile.getOriginalFilename()));
        PDDocument document = PDDocument.load(convFile);

        Splitter splitter = new Splitter();

        List<PDDocument> pages = splitter.split(document);

        Iterator<PDDocument> iterator = pages.listIterator();

        Partido partido = new Gson().fromJson(partidoString, Partido.class);

        Set<Ticket> ticketSet = new HashSet<>();


        for(int i = 1; iterator.hasNext();i++) {
            Ticket ticket = new Ticket();
            PDDocument pd = iterator.next();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            pd.save(baos);
            String base64String = Base64.getEncoder().encodeToString(baos.toByteArray());
            ticket.setPdfBase64(base64String);
            ticket.setEntrada(i);
            ticketSet.add(ticket);
            ticket.setPartido(partido);
            ticketRepo.save(ticket);
            partido.setTickets(ticketSet);
            partidoRepo.save(partido);
            pd.close();
        }

        document.close();
        return ticketSet.size();
    }


    public String decodeBase64ToPdf(UUID id) {

        Optional<Ticket> dbImageData = ticketRepo.findById(id);
        return null;
    }

}



