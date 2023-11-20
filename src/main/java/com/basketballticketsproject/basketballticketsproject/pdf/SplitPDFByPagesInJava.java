package com.basketballticketsproject.basketballticketsproject.pdf;




import com.aspose.pdf.Document;
import com.aspose.pdf.License;
import com.aspose.pdf.Page;
import com.aspose.pdf.PageCollection;
import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.util.Iterator;
import java.util.List;


public class SplitPDFByPagesInJava {
    public static void main(String[] args) throws Exception {
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