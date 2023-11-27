package com.basketballticketsproject.basketballticketsproject.service;

import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import static com.basketballticketsproject.basketballticketsproject.utils.Constants.PATH_CARPETA_FECHAS_PARTIDOS;
import static com.basketballticketsproject.basketballticketsproject.utils.Constants.PATH_CARPETA_PDF;

@Service
public class SplitPDFByPages {

    public void splitPdf(String fecha) throws IOException {
        File file = new File("TFG_BarManagment.pdf");
        PDDocument document = PDDocument.load(file);

        Splitter splitter = new Splitter();

        List<PDDocument> Pages = splitter.split(document);

        Iterator<PDDocument> iterator = Pages.listIterator();

        String path = PATH_CARPETA_FECHAS_PARTIDOS + "\\" + fecha;

        File carpetaConFechas = new File(path);
        if (!carpetaConFechas.exists()) {
            carpetaConFechas.mkdir();
        }

        for(int i = 1;iterator.hasNext();i++) {
            PDDocument pd = iterator.next();
            pd.save(path + "\\" + i + ".pdf");
        }

        document.close();
    }
}
