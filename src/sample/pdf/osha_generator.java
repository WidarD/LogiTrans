package sample.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class osha_generator {
    public static final String DEST = "sample/pdf";

    public static void generate_pdf(String name, String date) throws IOException {
        Document document = new Document();

        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(name+"_BHP.pdf"));
            document.open();
            BaseFont arial = BaseFont.createFont("sample/resources/font/Arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font arial24_bold=new Font(arial,24, Font.BOLD);
            Font arial18_bold=new Font(arial,18, Font.BOLD);
            Font arial16_italic=new Font(arial,16, Font.ITALIC);
            Font arial14=new Font(arial,14);
            Font arial12= new Font(arial, 12);
            Paragraph text = new Paragraph("ZAŚWIADCZENIE\n", arial24_bold);
            text.setAlignment(Element.ALIGN_CENTER);
            document.add(text);
            text = new Paragraph("o ukończeniu szkolenia w dziedzinie bezpieczeństwa i higieny pracy\n \n", arial16_italic);
            text.setAlignment(Element.ALIGN_CENTER);
            document.add(text);
            text = new Paragraph(name, arial18_bold);
            text.setAlignment(Element.ALIGN_CENTER);
            document.add(text);
            text = new Paragraph("W dniu "+date+" w Bydgoszczy ukończył szkolenie z zakresu bezpieczeństwa i higieny pracy \n podczas którego został zaznajomiony z podstawowymi " +
                    "zasadami dot. bezpieczeństwa i higieny pracy oraz został zapoznany z nowymi rozwiązaniami techniczno-organizacyjnymi w tym zakresie.\n \n \n \n", arial14);
            text.setAlignment(Element.ALIGN_CENTER);
            document.add(text);
            text = new Paragraph("Zaświadczenie wydano na podstawie "+"\u00A7"+"16 ust. 3 rozporządzenia Ministra Gospodarki i Pracy z dnia 27 lipca 2004 r. w sprawie szkolenia" +
                    " w dziedznie bezpieczeństwa i higieny pracy (Dz. U. Nr 180, poz.1860, z późn. zm.).\n \n \n", arial14);
            text.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(text);
            text = new Paragraph("..............................................................................................\n", arial12);
            text.setAlignment(Element.ALIGN_RIGHT);
            document.add(text);
            text = new Paragraph("podpis osoby upoważnionej przez organizatora szkolenia", arial12);
            text.setAlignment(Element.ALIGN_RIGHT);
            document.add(text);
            document.close();
            writer.close();
        } catch (DocumentException e){
            e.printStackTrace();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        }

    }

