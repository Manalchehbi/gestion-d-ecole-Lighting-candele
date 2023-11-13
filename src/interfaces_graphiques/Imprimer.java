package interfaces_graphiques;


import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * 
 */
public class Imprimer {

    /**
     * Default constructor
     */
    public static Font Arial14=FontFactory.getFont("arial.ttf", BaseFont.IDENTITY_H,16);
    
   
    public void imprimer(String type,int v) throws SQLException, ClassNotFoundException, DocumentException, IOException{
    	String requete="SELECT * FROM eleve WHERE type_eleve="+v;
    	
    	if(v==-1) requete="SELECT * FROM professeur";
    	
    	java.sql.ResultSet res=new ConnexionSql().connBD().createStatement().executeQuery(requete);
        Document doc = new Document(PageSize.A4);
        String nom=System.getProperty("user.home")+"/Liste des "+type+".pdf";
        PdfWriter writer=PdfWriter.getInstance(doc, new FileOutputStream(nom));
        doc.open();
        
        float w=doc.getPageSize().getWidth(),h=doc.getPageSize().getHeight();
        ColumnText col = new ColumnText(writer.getDirectContent());
        Rectangle r=new Rectangle(2*w/3, h-15,w-15, h-100);
        col.setSimpleColumn(r);
        col.setRunDirection(PdfWriter.RUN_DIRECTION_LTR);
        Paragraph a=new Paragraph();
        a=new Paragraph("Royaume du Maroc",Arial14);
        a.setAlignment(Element.ALIGN_CENTER);
        col.addElement(a);
        a=new Paragraph("Lighting candele",Arial14);
        a.setAlignment(Element.ALIGN_CENTER);
        col.addElement(a);
        col.go();
        
        doc.add(new Paragraph("\n\n\n"));
        a=new Paragraph("La liste des "+type+"\n\n",Arial14);
        a.setAlignment(Element.ALIGN_CENTER);
        doc.add(a);
        PdfPTable t=new PdfPTable(5);
        t.setWidthPercentage(100);
        t.setTotalWidth(new float[] {70, 300, 100,200,100});
        PdfPCell c;
        c=new PdfPCell(new Phrase("N°"));
        c.setBackgroundColor(BaseColor.LIGHT_GRAY);
        t.addCell(c);
        c=new PdfPCell(new Phrase("Nom Complete"));
        c.setBackgroundColor(BaseColor.LIGHT_GRAY);
        t.addCell(c);
        c=new PdfPCell(new Phrase("Age"));
        c.setBackgroundColor(BaseColor.LIGHT_GRAY);
        t.addCell(c);
        c=new PdfPCell(new Phrase("Date inscription"));
        c.setBackgroundColor(BaseColor.LIGHT_GRAY);
        t.addCell(c);
        c=new PdfPCell(new Phrase("Transport"));
        c.setBackgroundColor(BaseColor.LIGHT_GRAY);
        t.addCell(c);
        int i=0;
        System.err.println("imprimer");
        while(res.next()){
            t.addCell(i+"");
            t.addCell(res.getString("nom")+" "+res.getString("prenom"));
            t.addCell(res.getInt("age")+"");
            t.addCell(res.getDate("date_inscription").toString().toString());
            t.addCell(res.getString("transport"));
            i++;
        }
        doc.add(t);
        
        col.go();
        
        doc.newPage();
        doc.close();
        System.err.println("close");
        File file= new File(nom);
        Desktop.getDesktop().open(file);
    }
}