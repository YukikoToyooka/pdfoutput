

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.FileOutputStream;
import com.itextpdf.text.Document;


public class Main {

    public static void  main (String args[]) {

        try {

        Document document = new Document();

        PdfWriter.getInstance(document, new FileOutputStream("test.pdf"));

        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        Chunk chunk = new Chunk("Hello", font);

        document.add(chunk);
        document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
