package interfaces_graphiques;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
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
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class College extends JPanel {
	private JTable JTable_college;
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
	JComboBox combonbmat;
	private JCheckBoxMenuItem chektrnsport ;
	DefaultTableModel model;
	ButtonGroup g;
	private JTextField txtnbrow;
	private JTextField txtprix;
	private String identifiant;

	/**
	 * Create the panel.
	 */
	public College() {
	
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
		
		txtdatinsc.setBounds(509, 13, 122, 20);
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
		JRadioButton RdTransport = new JRadioButton("Transport");
		RdTransport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					transport();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		RdTransport.setForeground(Color.WHITE);
		RdTransport.setFont(new Font("Tahoma", Font.BOLD, 12));
		RdTransport.setBackground(new Color(204, 153, 255));
		RdTransport.setBounds(570, 232, 153, 23);
		panel.add(RdTransport);
		  g=new ButtonGroup();
	 	  g.add(RadioDateInscription);
	 	  g.add(radionom);
	 	  g.add(radioPrenom);
	 	 g.add(RdTransport);
	 
		JLabel lblNewLabel = new JLabel("Trier par :");
		lblNewLabel.setBounds(20, 238, 99, 14);
		panel.add(lblNewLabel);
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtnom.getText().toString().equals("") ||  txtprenom.getText().toString().equals("")||  comboBox.getSelectedItem().toString().equals("--Selectionner--")||  combonbmat.getSelectedItem().toString().equals("--Selectionner--")||  txtdatinsc.getDate()==null){
					JOptionPane.showMessageDialog(null, "il n'a pas d'\u00E9l\u00E8ve !.. veuillez remplir tous les champs ou chercher un \u00E9l\u00E8ve \u00A8 Modifier ! ");
				}else {
				int rep=JOptionPane.showConfirmDialog(null,"Voulez vous concerver la modification ?","Confirmation", JOptionPane.OK_CANCEL_OPTION,3);
				if(rep==0) {
					
					try {
						   java.sql.Date dob= new java.sql.Date(txtdatinsc.getDate().getTime());
						   if(chektrnsport.isSelected())
					        {
					             value = "Oui";

					        }else value ="Non";
							String query = ("UPDATE eleve SET nom=?,prenom=?,niveau=?,date_inscription=?,nb_matier=?, transport=? , montant =? where type_eleve='2' and numero='"+identifiant+"'");
							PreparedStatement pstmt = cnx.prepareStatement(query);
						    pstmt.setString(1, txtnom.getText());
						    pstmt.setString(2,  txtprenom.getText());
						    pstmt.setString(3,  comboBox.getSelectedItem().toString());
						    pstmt.setDate(4,dob);
						    pstmt.setString(5,  combonbmat.getSelectedItem().toString());
							pstmt.setString(6,  value);
							pstmt.setString(7,  txtprix.getText());
						    pstmt.executeUpdate();
					
						JOptionPane.showMessageDialog(null,"L'\u00E9l\u00E8ve est modifi\u00E9 avec succ\u00E8s !");
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
					JOptionPane.showMessageDialog(null, "il n'a pas d'\u00E9l\u00E8ve !.. veuillez remplir tous les champs ou chercher un \u00E9l\u00E8ve \u00A8 supprimer apartir de nom ! ");
				}else {
				int rep=JOptionPane.showConfirmDialog(null,"Voulez vous vraiment supprimer cet \u00E9l\u00E8ve ?","Confirmation", JOptionPane.OK_CANCEL_OPTION,3);
				if(rep==0) {
					
					try {
						st2= cnx.createStatement();
						st2.executeUpdate("DELETE FROM `payement` WHERE id_eleve="+identifiant);
						st2.executeUpdate("delete from eleve where type_eleve='2' and numero='"+identifiant+"'");
						txtnom.setText("");
						txtprenom.setText("");
						txtprix.setText("");
						comboBox.setSelectedItem("--Selectionner--");
						combonbmat.setSelectedItem("--Selectionner--");
						txtdatinsc.setDate(null);;
						JOptionPane.showMessageDialog(null,"l'\u00E9l\u00E8ve est supprim\u00E9");
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
				if(txtnom.getText().toString().equals("") ||  txtprenom.getText().toString().equals("")||  comboBox.getSelectedItem().toString().equals("--Selectionner--")||combonbmat.getSelectedItem().toString().equals("--Selectionner--")||  txtdatinsc.getDate()==null){
					JOptionPane.showMessageDialog(null, "Vueillez remplir tous les champs ! ");
				}else {
					java.sql.Date dob= new java.sql.Date(txtdatinsc.getDate().getTime());
					System.out.print(dob);
					if(chektrnsport.isSelected())
			        {
			             value = "Oui";

			        }
				String res = "INSERT INTO eleve (nom,prenom,niveau,date_inscription,nb_matier,transport,montant,type_eleve) VALUES (?,?,?,?,?,?,?,?)";
				try {
					ps = cnx.prepareStatement (res);
					
					ps.setString(1,txtnom.getText().toString());
					ps.setString(2,txtprenom.getText().toString());
					ps.setString(3,comboBox.getSelectedItem().toString());
					ps.setDate(4,dob);
					ps.setString(5,combonbmat.getSelectedItem().toString());
					ps.setString(6,value);
					ps.setString(7, txtprix.getText());
					ps.setInt(8,2);
					ps.execute();
					JOptionPane.showMessageDialog(null, "l'\u00E9l\u00E8ve "+txtprenom.getText()+" est ajout\u00E9 avec succ\u00E8s ! ");
					txtnom.setText("");
					txtprenom.setText("");
					txtprix.setText(" ");
					comboBox.setSelectedItem("--Selectionner--");
					combonbmat.setSelectedItem("--Selectionner--");
					txtdatinsc.setDate(null);
					chektrnsport.setSelected(false);
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
		lblNewLabel_1.setBounds(20, 13, 72, 32);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Pr\u00E9nom :");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel_1_1.setBounds(20, 60, 72, 32);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Niveau  :");
		lblNewLabel_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_2.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel_1_2.setBounds(20, 113, 72, 32);
		panel.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Date d'inscription :");
		lblNewLabel_1_3.setForeground(Color.WHITE);
		lblNewLabel_1_3.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel_1_3.setBounds(346, 0, 151, 32);
		panel.add(lblNewLabel_1_3);
	
		txtnom = new JTextField();
		txtnom.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String sql = "SELECT `Numero`, `nom`, `prenom`, `niveau`, `date_inscription`, `nb_matier`, `transport`, `montant`, `action` FROM `eleve` WHERE type_eleve='2' AND nom = ? ";
				try {
					pr=cnx.prepareStatement(sql);
					pr.setString(1, txtnom.getText().toString());
					rs3= pr.executeQuery();
					
					if(rs3.next()) {
						txtprenom.setText(rs3.getString(3));
						comboBox.getSelectedItem().toString().equals(rs3.getString(4));
						combonbmat.getSelectedItem().toString().equals(rs3.getString(5));
						txtdatinsc.setDate(rs3.getDate(5));
						txtprix.setText(rs3.getString(8));
						if(rs3.getString(7)=="Oui")
						chektrnsport.isSelected();
				       
						
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
					
				
				
			}
		});
		txtnom.setBounds(182, 13, 139, 20);
		panel.add(txtnom);
		txtnom.setColumns(10);
		
		txtprenom = new JTextField();
		txtprenom.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				prix();
			}
		});
		txtprenom.setColumns(10);
		txtprenom.setBounds(184, 68, 133, 20);
		panel.add(txtprenom);
		
		
		
		JButton btnAnnuler = new JButton("Vider");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtnom.getText().length()>0 ||  txtprenom.getText().length()>0 ||  comboBox.getSelectedItem().toString().length()>0 ||  txtdatinsc.getDate() != null){
					int rep=JOptionPane.showConfirmDialog(null,"Voulez vous annuler l'op\u00E9ration ?","Confirmation", JOptionPane.OK_CANCEL_OPTION,3);
					if(rep==0) {
						txtnom.setText("");
						txtprenom.setText("");
						comboBox.setSelectedItem("--Selectionner--");
						combonbmat.setSelectedItem("--Selectionner--");
						txtprix.setText("");
						txtdatinsc.setDate(null);
						chektrnsport.setSelected(false);
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
		
		 chektrnsport = new JCheckBoxMenuItem("Transport");
		 chektrnsport.addChangeListener(new ChangeListener() {
			 	public void stateChanged(ChangeEvent e) {
			 		prix();
			 	}
		});
		chektrnsport.setFont(new Font("Dialog", Font.BOLD, 13));
		chektrnsport.setForeground(Color.WHITE);
		chektrnsport.setBackground(new Color(204, 153, 255));
		chektrnsport.setBounds(346, 120, 99, 20);
		panel.add(chektrnsport);
		
		
		 comboBox = new JComboBox();
		comboBox.setBackground(new Color(255, 245, 238));
		comboBox.addItem("--Selectionner--");
		comboBox.addItem("1\u00E8re anne");
		comboBox.addItem("2\u00E8me ann\u00E9e");
		comboBox.addItem("3\u00E8me ann\u00E9e");
		comboBox.addItem("4\u00E8me ann\u00E9e");
		comboBox.addItem("5\u00E8me ann\u00E9e");
		comboBox.addItem("6\u00E8me ann\u00E9e");
		comboBox.setBounds(182, 120, 139, 22);
		panel.add(comboBox);
		
		 cmbtrie = new JComboBox();
		 cmbtrie.addKeyListener(new KeyAdapter() {
		 	@Override
		 	public void keyPressed(KeyEvent e) {
		 		if(!(comboBox.getSelectedItem().toString().equals("--Selectionner--"))) {
			 		try {
						niveau(comboBox.getSelectedItem().toString());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}}
		 	}
		 });
		 cmbtrie.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		if(!(comboBox.getSelectedItem().toString().equals("--Selectionner--"))) {
		 		try {
					niveau(comboBox.getSelectedItem().toString());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}}
		 	}
		 });
		
		combonbmat = new JComboBox();
		combonbmat.addItem("--Selectionner--");
		combonbmat.addItem("une mati\u00E8re");
		combonbmat.addItem("2 mati\u00E8res");
		combonbmat.addItem("3 mati\u00E8res");
		combonbmat.addItem("4 mati\u00E8res");
		combonbmat.addItem("5 mati\u00E8res");
		combonbmat.setBackground(new Color(255, 245, 238));
		combonbmat.setBounds(509, 67, 122, 22);
		combonbmat.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				prix();
			}
		});
		panel.add(combonbmat);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Nombre de mati\u00E8res  :");
		lblNewLabel_1_2_1.setForeground(Color.WHITE);
		lblNewLabel_1_2_1.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel_1_2_1.setBounds(349, 60, 179, 32);
		panel.add(lblNewLabel_1_2_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Montant :");
		lblNewLabel_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel_1_1_1.setBounds(509, 113, 72, 32);
		panel.add(lblNewLabel_1_1_1);
		
		txtprix = new JTextField();
		txtprix.setHorizontalAlignment(SwingConstants.CENTER);
		txtprix.setFont(new Font("Dialog", Font.BOLD, 13));
		txtprix.setEditable(false);
		txtprix.setColumns(10);
		txtprix.setBounds(586, 122, 43, 20);
		panel.add(txtprix);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("DH");
		lblNewLabel_1_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1_1.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel_1_1_1_1.setBounds(641, 113, 32, 32);
		panel.add(lblNewLabel_1_1_1_1);
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 300, 669, 292);
		add(scrollPane);
		
		JTable_college = new JTable();
		JTable_college.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {                                  
			      
		        DefaultTableModel model = (DefaultTableModel)JTable_college.getModel();
				int index = JTable_college.getSelectedRow();
				identifiant=model.getValueAt(index, 0).toString();
				txtnom.setText(JTable_college.getModel().getValueAt(index, 1).toString());
		     	txtprenom.setText(JTable_college.getModel().getValueAt(index, 2).toString());
		     	comboBox.setSelectedItem(JTable_college.getModel().getValueAt(index, 3).toString());
		     	combonbmat.setSelectedItem(JTable_college.getModel().getValueAt(index, 5).toString());
		     	txtprix.setText(JTable_college.getModel().getValueAt(index, 7).toString());
				java.util.Date selectin = (java.util.Date) model.getValueAt(index, 4);
				txtdatinsc.setDate(selectin);
				if(JTable_college.getModel().getValueAt(index, 6).toString().equals("Oui")) {
					chektrnsport.setSelected(true);
				}else  chektrnsport.setSelected(false);
				
				int colonne=JTable_college.getSelectedColumn();
				if(colonne==8) {
					String nom=JTable_college.getModel().getValueAt(index, 1)+" "+JTable_college.getModel().getValueAt(index, 2);
					int id=Integer.parseInt(JTable_college.getModel().getValueAt(index, 0).toString());
					Payement p;
					try {
						p = new Payement(id, nom);
						p.setVisible(true);
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
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
	
		scrollPane.setViewportView(JTable_college);
		
		JButton imprimer = new JButton("Imprimer");
		imprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new Imprimer().imprimer("coll\u00E8ge",2);
				} catch (ClassNotFoundException | SQLException | DocumentException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		imprimer.setForeground(new Color(148, 0, 211));
		imprimer.setFont(new Font("Dialog", Font.BOLD, 16));
		imprimer.setBounds(50, 591, 172, 32);
		add(imprimer);
		
		JLabel lblNewLabel_1_1_1_2 = new JLabel("Nombre des \u00E9l\u00E8ves :");
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
		 int nb =  JTable_college.getRowCount();
		 txtnbrow.setText(""+nb);
	}
	

	public  void Trier(String rd) throws SQLException {
	
		st1= cnx.createStatement();
		rs =st1.executeQuery("SELECT `Numero`, `nom`, `prenom`, `niveau`, `date_inscription`, `nb_matier`, `transport`, `montant`, `action` FROM `eleve` WHERE type_eleve='2' order by "+rd);
		JTable_college.setModel(DbUtils.resultSetToTableModel(rs));
		int nb =  JTable_college.getRowCount();
		txtnbrow.setText(""+nb);
	}
	public  void transport() throws SQLException {
	
		st1= cnx.createStatement();
		rs =st1.executeQuery("SELECT `Numero`, `nom`, `prenom`, `niveau`, `date_inscription`, `nb_matier`, `transport`, `montant`, `action` FROM `eleve` WHERE type_eleve='2' AND transport = 'Oui'");
		JTable_college.setModel(DbUtils.resultSetToTableModel(rs));
		int nb =  JTable_college.getRowCount();
		txtnbrow.setText(""+nb);
	}
	public  void niveau(String vl) throws SQLException {
	
		st1= cnx.createStatement();
		rs =st1.executeQuery("SELECT `Numero`, `nom`, `prenom`, `niveau`, `date_inscription`, `nb_matier`, `transport`, `montant`, `action` FROM `eleve` WHERE type_eleve='2' AND niveau = "+ vl);
		JTable_college.setModel(DbUtils.resultSetToTableModel(rs));
		int nb =  JTable_college.getRowCount();
		txtnbrow.setText(""+nb);
	}
	
	public void prix() {
		int v,i=combonbmat.getSelectedIndex();
		switch (i){
		case 1:
			v=50;
			break;
		case 2:
			v=60;
			break;
		case 3:
			v=70;
			break;
		case 4:
			v=80;
			break;
		case 5:
			v=90;
			break;
		default:
			v=0;
		}
		if(chektrnsport.isSelected())
			txtprix.setText(v+100*i+"");
		else txtprix.setText(100*i+"");
	}

}
