package sample.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class schedule_generator {
    public static void generate_pdf(String date, String name, String counterparty, String route) throws IOException, DocumentException {
        Document document = new Document();
        BaseFont arial = BaseFont.createFont("sample/resources/font/Arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(name + "_GRAFIK" + ".pdf"));
            //BaseFont arial = BaseFont.createFont("sample/resources/font/Arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);


            Font arial24_bold = new Font(arial, 24, Font.BOLD);
            Font arial12 = new Font(arial, 12);
            document.open();
            Paragraph text1 = new Paragraph("ZLECENIE\n \n \n", arial24_bold);
            text1.setAlignment(Element.ALIGN_CENTER);
            document.add(text1);
            PdfPTable table1 = new PdfPTable(2);

            table1.addCell("Pracownik:");
            table1.addCell(new PdfPCell(new Phrase(name,arial12)));
            table1.addCell("Data zlecenia:");
            table1.addCell(date);
            table1.addCell("Kontrahent:");
            table1.addCell(counterparty);
            table1.addCell("Trasa:");
            table1.addCell(new PdfPCell(new Phrase(route,arial12)));
            document.add(table1);
            text1 = new Paragraph("\n \n \n ..............................................................................................\n", arial12);
            text1.setAlignment(Element.ALIGN_RIGHT);
            document.add(text1);
            text1 = new Paragraph("podpis zleceniodawcy", arial12);
            text1.setAlignment(Element.ALIGN_RIGHT);
            document.add(text1);
            document.close();
            writer.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }

    }
}
