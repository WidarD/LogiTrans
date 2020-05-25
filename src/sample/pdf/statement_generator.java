package sample.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class statement_generator {
    public static void generate_pdf(String date, String name, String counterparty, String route, String number) throws IOException, DocumentException {
        Document document = new Document();
        BaseFont arial = BaseFont.createFont("sample/resources/font/Arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font arial24_bold = new Font(arial, 24, Font.BOLD);
        Font arial12 = new Font(arial, 12);

        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(name + "_ROZLICZENIE" + ".pdf"));
            document.open();
            Paragraph text1 = new Paragraph("ROZLICZENIE\n \n \n", arial24_bold);
            text1.setAlignment(Element.ALIGN_CENTER);
            document.add(text1);
            PdfPTable table1 = new PdfPTable(2);
            table1.addCell("Pracownik:");
            table1.addCell(name);
            table1.addCell("Data zlecenia:");
            table1.addCell(date);
            table1.addCell("Kontrahent:");
            table1.addCell(counterparty);
            table1.addCell("Trasa:");
            table1.addCell(route);
            table1.addCell("Liczba sztuk:");
            table1.addCell(number);
            document.add(table1);
            text1 = new Paragraph("\n \n \n ..............................................................................................\n", arial12);
            text1.setAlignment(Element.ALIGN_RIGHT);
            document.add(text1);
            text1 = new Paragraph("podpis osoby potwierdzającej odbiór", arial12);
            text1.setAlignment(Element.ALIGN_RIGHT);
            document.add(text1);
            document.close();
            writer.close();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
