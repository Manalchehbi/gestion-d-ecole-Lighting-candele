package interfaces_graphiques;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.DocumentException;
import com.mysql.cj.protocol.Resultset;
import com.toedter.calendar.JDateChooser;

import net.proteanit.sql.DbUtils;

import java.sql.Connection;
import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.Font;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JCheckBoxMenuItem;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JComboBox;

public class Professeurs extends JPanel {
	private JTable JTable_professeur;
	java.sql.Connection cnx=null;
	ResultSet rs=null;
	ResultSet rs3,rs2=null;
	Statement st2,st =null;
	String value;
	PreparedStatement pr,pstmt,ps=null;
	Statement st1=null;
	private JTextField txtnom;
	private JTextField txtprenom;
	java.sql.Date dob;
	JComboBox comboBox;
	JComboBox cmbtrie;
	private JDateChooser txtdatinsc;
	DefaultTableModel model;
	ButtonGroup g;
	private JTextField txtsalaire;
	private JTextField txtnbrow;
	private String identifiant;

	/**
	 * Create the panel.
	 */
	public Professeurs() {
	
		setBounds(0, 0, 689, 643);
		setLayout(null);
		
		try {
			cnx = ConnexionSql.connBD();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 153, 255));
		panel.setBounds(10, 11, 669, 278);
		add(panel);
		panel.setLayout(null);
		
		JRadioButton RadioDateInscription = new JRadioButton("Date d'inscription");
		RadioDateInscription.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		RadioDateInscription.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Trier("date_inscription");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		txtdatinsc =  new JDateChooser();
		//txtdatinsc.setDateFormatString("yyyy-mm-dd");
		
		txtdatinsc.setBounds(560, 30, 99, 20);
		panel.add(txtdatinsc);
		RadioDateInscription.setFont(new Font("Tahoma", Font.BOLD, 12));
		RadioDateInscription.setForeground(Color.WHITE);
		RadioDateInscription.setBackground(new Color(204, 153, 255));
		RadioDateInscription.setBounds(370, 232, 153, 23);
		panel.add(RadioDateInscription);
		
		JRadioButton radionom = new JRadioButton("Nom");
		radionom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Trier("nom");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		radionom.setForeground(Color.WHITE);
		radionom.setFont(new Font("Tahoma", Font.BOLD, 12));
		radionom.setBackground(new Color(204, 153, 255));
		radionom.setBounds(125, 232, 109, 23);
		panel.add(radionom);
		JRadioButton radioPrenom = new JRadioButton("Pr\u00E9nom");
		radioPrenom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Trier("prenom");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		radioPrenom.setForeground(Color.WHITE);
		radioPrenom.setFont(new Font("Tahoma", Font.BOLD, 12));
		radioPrenom.setBackground(new Color(204, 153, 255));
		radioPrenom.setBounds(250, 232, 109, 23);
		panel.add(radioPrenom);
		  g=new ButtonGroup();
	 	  g.add(RadioDateInscription);
	 	  g.add(radionom);
	 	  g.add(radioPrenom);
	 
		JLabel lblNewLabel = new JLabel("Trier par :");
		lblNewLabel.setBounds(20, 238, 99, 14);
		panel.add(lblNewLabel);
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtnom.getText().toString().equals("") ||  txtprenom.getText().toString().equals("")||  comboBox.getSelectedItem().toString().equals("--Selectionner--")||  txtdatinsc.getDate()==null){
					JOptionPane.showMessageDialog(null, "il n'a pas de professeur !.. veuillez remplir tous les champs ou chercher un professeur � modifier ! ");
				}else {
				int rep=JOptionPane.showConfirmDialog(null,"Voulez vous concerver la modification ?","Confirmation", JOptionPane.OK_CANCEL_OPTION,3);
				if(rep==0) {
					
					try {
						   java.sql.Date dob= new java.sql.Date(txtdatinsc.getDate().getTime());
						  
							String query = ("UPDATE professeur SET nom=?,prenom=?,date_inscription=?, nb_matieres=? , salaire=? where id='"+identifiant+"'");
							PreparedStatement pstmt = cnx.prepareStatement(query);
						    pstmt.setString(1, txtnom.getText());
						    pstmt.setString(2,  txtprenom.getText());
						    pstmt.setDate(3,dob);
						    pstmt.setString(4,  comboBox.getSelectedItem().toString());
							pstmt.setString(5,  txtsalaire.getText());
						    pstmt.executeUpdate();
					
						JOptionPane.showMessageDialog(null,"le professeur est modifi\u00E9 avec succ\u00E8s !");
						try {
							Trier("nom");
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						
					} catch (SQLException  e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}}
				
			}
		});
		btnModifier.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnModifier.setForeground(Color.WHITE);
		btnModifier.setBackground(new Color(148, 0, 211));
		btnModifier.setBounds(182, 172, 139, 32);
		panel.add(btnModifier);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtnom.getText().toString().equals("") ||  txtprenom.getText().toString().equals("")||  comboBox.getSelectedItem().toString().equals("--Selectionner--")||  txtdatinsc.getDate()==null){
					JOptionPane.showMessageDialog(null, "il n'a pas de professeur !.. veuillez remplir tous les champs ou chercher un professeur \u00A8 supprimer apartir de nom ! ");
				}else {
				int rep=JOptionPane.showConfirmDialog(null,"Voulez vous vraiment supprimer ce professeur ?","Confirmation", JOptionPane.OK_CANCEL_OPTION,3);
				
				if(rep==0) {
					
					try {
						st2= cnx.createStatement();
						st2.executeUpdate("delete from professeur where id='"+identifiant+"'");
						txtnom.setText("");
						txtprenom.setText("");
						txtsalaire.setText("");
						comboBox.setSelectedItem("--Selectionner--");
						txtdatinsc.setDate(null);;
						JOptionPane.showMessageDialog(null,"le professeur est supprim\u00E9");
						try {
							Trier("nom");
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
					
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}}
				
			}}
		});
		btnSupprimer.setForeground(Color.WHITE);
		btnSupprimer.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSupprimer.setBackground(new Color(148, 0, 211));
		btnSupprimer.setBounds(520, 172, 139, 32);
		panel.add(btnSupprimer);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtnom.getText().toString().equals("") ||  txtprenom.getText().toString().equals("")||  txtsalaire.getText().toString().equals("")||  comboBox.getSelectedItem().toString().equals("--Selectionner--")||  txtdatinsc.getDate()==null){
					JOptionPane.showMessageDialog(null, "Vueillez remplir tous les champs ! ");
				}else {
					java.sql.Date dob= new java.sql.Date(txtdatinsc.getDate().getTime());
				
					
				String res = "insert into professeur (nom,prenom,date_inscription,nb_matieres,salaire) values (?,?,?,?,?)";
				try {
					ps = cnx.prepareStatement (res);
					
					
					
					ps.setString(1,txtnom.getText().toString());
					ps.setString(2,txtprenom.getText().toString());
					ps.setDate(3,dob);
					ps.setString(4,comboBox.getSelectedItem().toString());
					
					ps.setString(5,txtsalaire.getText().toString());
					ps.execute();
					JOptionPane.showMessageDialog(null, "le professeur  "+txtprenom.getText()+" est ajout\u00E9 avec succ\u00E8s ! ");
					txtnom.setText("");
					txtprenom.setText("");
					comboBox.setSelectedItem("--Selectionner--");
					txtsalaire.setText("");
					txtdatinsc.setDate(null);
					
					try {
						Trier("nom");
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
			}
		});
		btnAjouter.setForeground(Color.WHITE);
		btnAjouter.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAjouter.setBackground(new Color(148, 0, 211));
		btnAjouter.setBounds(20, 172, 133, 32);
		panel.add(btnAjouter);
		
		JLabel lblNewLabel_1 = new JLabel("Nom :");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(20, 21, 72, 32);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Pr\u00E9nom :");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel_1_1.setBounds(223, 21, 72, 32);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Nombre de mati\u00E8res :");
		lblNewLabel_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_2.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel_1_2.setBounds(20, 95, 168, 32);
		panel.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Date de travail :");
		lblNewLabel_1_3.setForeground(Color.WHITE);
		lblNewLabel_1_3.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel_1_3.setBounds(425, 21, 151, 32);
		panel.add(lblNewLabel_1_3);
	
		txtnom = new JTextField();
		txtnom.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String sql = "select * from professeur where nom = ? ";
				try {
					pr=cnx.prepareStatement(sql);
					pr.setString(1, txtnom.getText().toString());
					rs3= pr.executeQuery();
					
					if(rs3.next()) {
						txtprenom.setText(rs3.getString(3));
						txtdatinsc.setDate(rs3.getDate(4));
						comboBox.getSelectedItem().toString().equals(rs3.getString(5));
						txtsalaire.setText(rs3.getString(6));
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
					
				
				
			}
		});
		txtnom.setBounds(79, 30, 123, 20);
		panel.add(txtnom);
		txtnom.setColumns(10);
		
		txtprenom = new JTextField();
		txtprenom.setColumns(10);
		txtprenom.setBounds(305, 30, 99, 20);
		panel.add(txtprenom);
		
		
		
		JButton btnAnnuler = new JButton("Vider");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtnom.getText().length()>0 ||  txtprenom.getText().length()>0 ||  comboBox.getSelectedItem().toString().length()>0 ||  txtdatinsc.getDate() != null ||   txtsalaire.getText().length()>0){
					int rep=JOptionPane.showConfirmDialog(null,"Voulez vous annuler l'op\u00E9ration ?","Confirmation", JOptionPane.OK_CANCEL_OPTION,3);
					if(rep==0) {
						txtnom.setText("");
						txtprenom.setText("");
						comboBox.setSelectedItem("--Selectionner--");
						txtdatinsc.setDate(null);
						txtsalaire.setText("");
				}else {
					
				
					
				
				}
				
			}
			}
		});
		btnAnnuler.setForeground(Color.WHITE);
		btnAnnuler.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAnnuler.setBackground(new Color(148, 0, 211));
		btnAnnuler.setBounds(349, 172, 139, 32);
		panel.add(btnAnnuler);
		
		 comboBox = new JComboBox();
		comboBox.setBackground(new Color(255, 245, 238));
		comboBox.addItem("--Selectionner--");
		comboBox.addItem("une mati\u00E8re");
		comboBox.addItem("2 mati\u00E8res");
		comboBox.addItem("3 mati\u00E8res");
		comboBox.addItem("4 mati\u00E8res");
		comboBox.addItem("5 mati\u00E8res");
		comboBox.setBounds(223, 103, 181, 22);
		panel.add(comboBox);
		
		 cmbtrie = new JComboBox();
		 cmbtrie.addKeyListener(new KeyAdapter() {
		 	@Override
		 	public void keyPressed(KeyEvent e) {
		 		
		 	}
		 });
		 cmbtrie.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		
		 	}
		 });
	
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Salaire :");
		lblNewLabel_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel_1_1_1.setBounds(425, 95, 72, 32);
		panel.add(lblNewLabel_1_1_1);
		
		txtsalaire = new JTextField();
		txtsalaire.setColumns(10);
		txtsalaire.setBounds(560, 104, 99, 20);
		panel.add(txtsalaire);
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 300, 669, 292);
		add(scrollPane);
		
		JTable_professeur = new JTable();
		JTable_professeur.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {                                  
			      
		        DefaultTableModel model = (DefaultTableModel)JTable_professeur.getModel();
				int index = JTable_professeur.getSelectedRow();
				identifiant=model.getValueAt(index, 0).toString();
				txtnom.setText(JTable_professeur.getModel().getValueAt(index, 1).toString());
		     	txtprenom.setText(JTable_professeur.getModel().getValueAt(index, 2).toString());
		     	java.util.Date selectin = (java.util.Date) model.getValueAt(index, 3);
				txtdatinsc.setDate(selectin);
		     	comboBox.setSelectedItem(JTable_professeur.getModel().getValueAt(index, 4).toString());
		     	txtsalaire.setText(JTable_professeur.getModel().getValueAt(index, 5).toString());
		        
//				if(JTable_primaire.getModel().getValueAt(index, 5).toString().equals("Oui")) {
//					chektrnsport.setSelected(true);
//				}else  chektrnsport.setSelected(false);
				
		    }   });
//		JTable_primaire.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				int ligne = JTable_primaire.getSelectedRow();
//				txtnom.setText(JTable_creche.getModel().getValueAt(ligne, 1).toString());
//				txtprenom.setText(JTable_creche.getModel().getValueAt(ligne, 2).toString());
//				txtage.setText(JTable_creche.getModel().getValueAt(ligne, 4).toString());
//				
//				DefaultTableModel model = (DefaultTableModel)JTable_creche.getModel();
//				try {
//	          Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String)model.getValueAt(ligne, 4));
//					txtdatinsc.setDate(date);
//				} catch (ParseException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//	            
//						
//						if(JTable_creche.getModel().getValueAt(ligne, 5).toString()=="Oui") {
//						chektrnsport.isSelected();
//						}
//					
//				}
//				
//			});
	
		scrollPane.setViewportView(JTable_professeur);
		
		JButton imprimer = new JButton("Imprimer");
		imprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				try {
//					new Imprimer().imprimer("professeurs",-1);
//				} catch (ClassNotFoundException | SQLException | DocumentException | IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
			}
		});
		imprimer.setForeground(new Color(148, 0, 211));
		imprimer.setFont(new Font("Dialog", Font.BOLD, 16));
		imprimer.setBounds(50, 591, 172, 32);
		add(imprimer);
 
		JLabel lblNewLabel_1_1_1_2 = new JLabel("Nombre des professeurs :");
		lblNewLabel_1_1_1_2.setForeground(new Color(148, 0, 211));
		lblNewLabel_1_1_1_2.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel_1_1_1_2.setBounds(387, 587, 172, 32);
		add(lblNewLabel_1_1_1_2);
		
		txtnbrow = new JTextField();
		txtnbrow.setForeground(new Color(255, 255, 255));
		txtnbrow.setBackground(new Color(148, 0, 211));
		txtnbrow.setHorizontalAlignment(SwingConstants.CENTER);
		txtnbrow.setFont(new Font("Dialog", Font.BOLD, 13));
		txtnbrow.setEditable(false);
		txtnbrow.setColumns(10);
		txtnbrow.setBounds(564, 596, 98, 20);
		add(txtnbrow);
		
		 try {
				Trier("nom");
			} catch (SQLException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
		 int nb =  JTable_professeur.getRowCount();
			txtnbrow.setText(""+nb);
	}
	

public  void Trier(String rd) throws SQLException {
	st1= cnx.createStatement();
	rs =st1.executeQuery("select * from professeur order by "+rd);
	JTable_professeur.setModel(DbUtils.resultSetToTableModel(rs));
	int nb =  JTable_professeur.getRowCount();
	txtnbrow.setText(""+nb);
}


}
