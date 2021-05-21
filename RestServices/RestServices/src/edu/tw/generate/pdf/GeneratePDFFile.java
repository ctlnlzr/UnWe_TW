package edu.tw.generate.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import edu.tw.database.entity.TotalPerMonth;
import edu.tw.database.entitymanager.EntityManagerProvider;
import edu.tw.database.repository.TotalPerMonthRepository;

import javax.persistence.EntityManager;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;


public class GeneratePDFFile {

    private static void insertCell(PdfPTable table, String text, int align, int colspan, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));
        cell.setHorizontalAlignment(align);
        cell.setColspan(colspan);
        if (text.trim().equalsIgnoreCase("")) {
            cell.setMinimumHeight(10f);
        }
        table.addCell(cell);

    }


    public static void main(String[] args) throws FileNotFoundException, DocumentException {


        EntityManager entityManager = EntityManagerProvider.getEntityManagerFactory().createEntityManager();
        Font bfBold12 = new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD, new BaseColor(0, 0, 0));
        TotalPerMonthRepository totalPerMonthRepository = new TotalPerMonthRepository(entityManager);
        List<TotalPerMonth> all = totalPerMonthRepository.all();
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\strat\\Desktop\\cv.pdf"));
        document.open();
        Paragraph paragraph = new Paragraph();
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        insertCell(table, "Luna ", Element.ALIGN_RIGHT, 1, bfBold12);
        insertCell(table, "Judetul ", Element.ALIGN_RIGHT, 1, bfBold12);
        insertCell(table, "Total", Element.ALIGN_RIGHT, 1, bfBold12);
        insertCell(table, "Rata ", Element.ALIGN_RIGHT, 1, bfBold12);
        table.setHeaderRows(1);
        //table.setSpacingBefore(10f);
        //table.setSpacingAfter(10f);
        //float[] columnWidths = {1f, 1f, 1f, 1f};
        //table.setWidths(columnWidths);
        for (int i = 0; i < all.size(); i++) {
            insertCell(table, String.valueOf(all.get(i).getLuna()), Element.ALIGN_RIGHT, 1, bfBold12);
            insertCell(table, all.get(i).getJudet(), Element.ALIGN_RIGHT, 1, bfBold12);
            insertCell(table, String.valueOf(all.get(i).getTotal()), Element.ALIGN_RIGHT, 1, bfBold12);
            insertCell(table, String.valueOf(all.get(i).getRata()), Element.ALIGN_RIGHT, 1, bfBold12);
        }

        paragraph.add(table);
        document.add(paragraph);
        document.close();
        writer.close();

    }
}
