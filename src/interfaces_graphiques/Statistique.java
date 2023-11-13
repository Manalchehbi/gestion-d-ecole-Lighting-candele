package interfaces_graphiques;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import com.mysql.cj.xdevapi.Statement;


public class Statistique extends JPanel {

	/**
	 * Create the panel.
	 */
	int i=1;
	java.sql.Connection cnx=null;
	java.sql.Statement stm=null;	
	public Statistique() throws ClassNotFoundException, SQLException {
		cnx=ConnexionSql.connBD();
		stm=cnx.createStatement();
		setBounds(0, 0, 689, 643);
		setLayout(null);
		setBackground(new Color(75, 0, 130));
		
		
        
        add(affiche());
	}
	
	public JPanel affiche() throws SQLException {
		JPanel pan = new JPanel();
		pan.setBounds(10, 10, 660, 603);
		pan.setLayout(null);
		
		
		java.sql.ResultSet res;
		res=stm.executeQuery("SELECT COUNT(*) FROM eleve WHERE transport='Oui'");
		res.next();
        double trans_oui=new Double(res.getObject(1).toString());
        res=stm.executeQuery("SELECT COUNT(*) FROM eleve WHERE transport='Non'");
		res.next();
        double trans_non=new Double(res.getObject(1).toString());
		double t=trans_oui+trans_non;
		DefaultPieDataset data1 = new DefaultPieDataset( );
        data1.setValue( "Avec Transport" , trans_oui/t );
        data1.setValue( "Sans Transport" , trans_non/t );
        
        JFreeChart chart = ChartFactory.createPieChart("Paurcentage de transport", data1, true, true, false);
 
        ChartPanel p=new ChartPanel( chart );
        p.setBounds(10, 300, 300, 300);
        pan.add(p);
//=======================================================================================================================
        
        
        res=stm.executeQuery("SELECT COUNT(*) FROM payement");
		res.next();
        double paye=new Double(res.getObject(1).toString());
        res=stm.executeQuery("SELECT COUNT(*) FROM eleve");
		res.next();
        double total=new Double(res.getObject(1).toString());
        
        DefaultPieDataset data2 = new DefaultPieDataset( );
        data2.setValue( "Paye" , paye/total);
        data2.setValue( "Non Paye" , (total-paye)/total );
        
        JFreeChart ch = ChartFactory.createPieChart("Payements du mois actuel", data2, true, true, false);
 
        ChartPanel pp=new ChartPanel( ch );
        pp.setBounds(340, 300, 320, 300);
        pan.add(pp);
      //=======================================================================================================================
        
        
        res=stm.executeQuery("SELECT COUNT(*) FROM eleve WHERE type_eleve='0'");
		res.next();
        double creche=new Double(res.getObject(1).toString());
        res=stm.executeQuery("SELECT COUNT(*) FROM eleve WHERE type_eleve='1'");
		res.next();
        double primaire=new Double(res.getObject(1).toString());
        res=stm.executeQuery("SELECT COUNT(*) FROM eleve WHERE type_eleve='2'");
		res.next();
        double college=new Double(res.getObject(1).toString());
        res=stm.executeQuery("SELECT COUNT(*) FROM eleve WHERE type_eleve='3'");
		res.next();
        double lycee=new Double(res.getObject(1).toString());
        
        DefaultCategoryDataset data3 =new DefaultCategoryDataset( );
        data3.addValue( creche  ,"Elèves", "crèche" );
        data3.addValue( primaire  ,"Elèves", "primaire" );
        data3.addValue( college  ,"Elèves", "college" );
        data3.addValue( lycee  ,"Elèves", "Lycee" );
        JFreeChart barChart = ChartFactory.createBarChart("Pourcentage des élèves", "", "", data3, PlotOrientation.VERTICAL, true, true, false);
        
        ChartPanel tp=new ChartPanel( barChart );
        tp.setBounds(100, 10, 450, 278);
      //=======================================================================================================================
        pan.add(tp);
		return pan;
	}
	

}
