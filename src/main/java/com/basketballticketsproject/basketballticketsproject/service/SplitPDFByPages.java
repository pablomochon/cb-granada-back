package com.basketballticketsproject.basketballticketsproject.service;

import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@Service
public class SplitPDFByPages {

    public void splitPdf() throws IOException {
        File file = new File("TFG_BarManagment.pdf");
        PDDocument document = PDDocument.load(file);

        Splitter splitter = new Splitter();

        List<PDDocument> Pages = splitter.split(document);

        Iterator<PDDocument> iterator = Pages.listIterator();

        for(int i = 1;iterator.hasNext();i++)
        {
            PDDocument pd = iterator.next();
            pd.save("C:\\PDF_PRUEBA\\" + i + ".pdf");
        }

        document.close();
    }
}
