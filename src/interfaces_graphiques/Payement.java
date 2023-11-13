package interfaces_graphiques;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.xdevapi.Statement;



public class Payement extends JFrame {
	private java.sql.Connection cnx;
	private PreparedStatement ps=null;
	private ResultSet res=null;
	private int id=0;
	boolean paye;
	private String nom=null;
	private final JPanel contentPanel = new JPanel();

	private JLabel Information, Septembre, Octobre, Novembre, Decembre, Janvier, Fevrier, Mars, Avril, Mai, Juin, Juillet;
	private JPanel contentPane;

	
	public Payement(int id,String nom) throws ClassNotFoundException, SQLException{
		setResizable(false);
		this.nom=nom;
		this.id=id;

		
		

		cnx=ConnexionSql.connBD();
		java.sql.Statement stm=cnx.createStatement();
	
		String req="SELECT mois_paye FROM payement WHERE  id_eleve= "+id;////////ajout id
		res= stm.executeQuery(req);
		
		ArrayList<Integer> mois= new ArrayList();
		
		
		while(((ResultSet) res).next()) {
			mois.add(res.getInt(1));
			
		}
		for(Integer moi : mois)
		 System.out.println(moi);
		
		
		setBounds(100, 100, 600, 500);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		Information = new JLabel("La liste des mois de payement de "+nom);
		Information.setForeground(new Color(148, 0, 211));
		Information.setFont(new Font("Tahoma", Font.BOLD, 15));
		Information.setBounds(164, 30, 395, 34);
		//txtpnInformation.setBackground(new Color(148, 0, 211));
		Information.setOpaque(true);
		contentPanel.add(Information);
		
		Decembre = new JLabel("Decembre");
		Decembre.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Decembre.setHorizontalAlignment(SwingConstants.CENTER);
		Decembre.setForeground(new Color(255, 255, 255));
		Decembre.setBackground(new Color(148, 0, 211));
		Decembre.setFont(new Font("Tahoma", Font.BOLD, 14));
		Decembre.setBounds(426, 126, 73, 34);
		Decembre.setOpaque(true);
			if(mois.contains(12)) {
				Decembre.setBackground(Color.green);
			}
		
		contentPanel.add(Decembre);
		Decembre.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if( Decembre.getBackground().getRGB()==new Color(148, 0, 211).getRGB() ) {
					if(JOptionPane.showConfirmDialog(null,"Voullez vous ajouter le payement de mois Decembre ?","Confirmation",JOptionPane.OK_CANCEL_OPTION,3)==0) {
						try {
							addPayment(id,12);
							Decembre.setBackground(Color.green);
						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}else {
					if(JOptionPane.showConfirmDialog(null,"Voullez vous Supprimer le payement de mois Decembre ?","Confirmation",JOptionPane.OK_CANCEL_OPTION,3)==0) {
						try {
							removePayment(id,12);
							Decembre.setBackground(new Color(148, 0, 211));
						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});
//**************************************
		Novembre = new JLabel("Novembre");
		Novembre.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Novembre.setHorizontalAlignment(SwingConstants.CENTER);
		Novembre.setForeground(new Color(255, 255, 255));
		Novembre.setBackground(new Color(148, 0, 211));
		Novembre.setFont(new Font("Tahoma", Font.BOLD, 14));
		Novembre.setBounds(306, 126, 73, 34);
		Novembre.setOpaque(true);
		if(mois.contains(11)) {
			
			Novembre.setBackground(Color.green);
		}
		contentPanel.add(Novembre);
		Novembre.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(Novembre.getBackground().getRGB()==new Color(148, 0, 211).getRGB()) {
					if(JOptionPane.showConfirmDialog(null,"Voullez vous ajouter le payement de mois Novembre ?","Confirmation",JOptionPane.OK_CANCEL_OPTION,3)==0) {
						try {
							addPayment(id,11);
							Novembre.setBackground(Color.green);
						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}else {
					if(JOptionPane.showConfirmDialog(null,"Voullez vous Supprimer le payement de mois Novembre ?","Confirmation",JOptionPane.OK_CANCEL_OPTION,3)==0) {
						try {
							removePayment(id,11);
							Novembre.setBackground(new Color(148, 0, 211));
						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});
	//****************************
	
		Septembre = new JLabel("Septembre");
		Septembre.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Septembre.setForeground(new Color(255, 255, 255));
		Septembre.setBackground(new Color(148, 0, 211));
		Septembre.setHorizontalAlignment(SwingConstants.CENTER);
		Septembre.setFont(new Font("Tahoma", Font.BOLD, 14));
		Septembre.setBounds(75, 126, 85, 34);
		Septembre.setOpaque(true);
		contentPanel.add(Septembre);
		
		if(mois.contains(9)) {
			Septembre.setBackground(Color.green);
			
		}
		Septembre.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(Septembre.getBackground().getRGB()==new Color(148, 0, 211).getRGB()) {
					if(JOptionPane.showConfirmDialog(null,"Voullez vous ajouter le payement de mois Septembre ?","Confirmation",JOptionPane.OK_CANCEL_OPTION,3)==0) {
						try {
							addPayment(id,9);
							Septembre.setBackground(Color.green);
						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}else {
					if(JOptionPane.showConfirmDialog(null,"Voullez vous Supprimer le payement de mois Septembre ?","Confirmation",JOptionPane.OK_CANCEL_OPTION,3)==0) {
						try {
							removePayment(4,9);
							Septembre.setBackground(new Color(148, 0, 211));;
						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});
	
		contentPanel.add(Septembre);
	
	//****************************
		Octobre = new JLabel("Octobre");
		Octobre.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Octobre.setHorizontalAlignment(SwingConstants.CENTER);
		Octobre.setForeground(new Color(255, 255, 255));
		Octobre.setBackground(new Color(148, 0, 211));
		Octobre.setFont(new Font("Tahoma", Font.BOLD, 14));
		Octobre.setBounds(196, 126, 73, 34);
		Octobre.setOpaque(true);
		if(mois.contains(10)) {
			Octobre.setBackground(Color.green);
		}
		contentPanel.add(Octobre);
		Octobre.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(Octobre.getBackground().getRGB()==new Color(148, 0, 211).getRGB()) {
					if(JOptionPane.showConfirmDialog(null,"Voullez vous ajouter le payement de mois Octobre ?","Confirmation",JOptionPane.OK_CANCEL_OPTION,3)==0) {
						try {
							addPayment(id,10);
							Octobre.setBackground(Color.green);
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}else {
					if(JOptionPane.showConfirmDialog(null,"Voullez vous Supprimer le payement de mois Octobre ?","Confirmation",JOptionPane.OK_CANCEL_OPTION,3)==0) {
						try {
							removePayment(id,10);
							Octobre.setBackground(new Color(148, 0, 211));
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});
	//****************************
		Mars = new JLabel("Mars");
		Mars.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Mars.setForeground(new Color(255, 255, 255));
		Mars.setHorizontalAlignment(SwingConstants.CENTER);
		Mars.setBackground(new Color(148, 0, 211));
		Mars.setFont(new Font("Tahoma", Font.BOLD, 14));
		Mars.setBounds(306, 196, 73, 34);
		Mars.setOpaque(true);
		if(mois.contains(3)) {
			Mars.setBackground(Color.green);
		}
		contentPanel.add(Mars);
		Mars.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(Mars.getBackground().getRGB()==new Color(148, 0, 211).getRGB()) {
					if(JOptionPane.showConfirmDialog(null,"Voullez vous ajouter le payement de mois Mars ?","Confirmation",JOptionPane.OK_CANCEL_OPTION,3)==0) {
						try {
							addPayment(id,3);
							Mars.setBackground(Color.green);
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}else {
					if(JOptionPane.showConfirmDialog(null,"Voullez vous Supprimer le payement de mois Mars ?","Confirmation",JOptionPane.OK_CANCEL_OPTION,3)==0) {
						try {
							removePayment(id,3);
							Mars.setBackground(new Color(148, 0, 211));
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});
	//****************************
		Fevrier = new JLabel("Février");
		Fevrier.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Fevrier.setHorizontalAlignment(SwingConstants.CENTER);
		Fevrier.setForeground(new Color(255, 255, 255));
		Fevrier.setBackground(new Color(148, 0, 211));
		Fevrier.setFont(new Font("Tahoma", Font.BOLD, 14));
		Fevrier.setBounds(196, 196, 73, 34);
		Fevrier.setOpaque(true);
		if(mois.contains(2)) {
			Fevrier.setBackground(Color.green);
		}
		contentPanel.add(Fevrier);
		Fevrier.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(Fevrier.getBackground().getRGB()==new Color(148, 0, 211).getRGB()) {
					if(JOptionPane.showConfirmDialog(null,"Voullez vous ajouter le payement de mois Février ?","Confirmation",JOptionPane.OK_CANCEL_OPTION,3)==0) {
						try {
							addPayment(id,2);
							Fevrier.setBackground(Color.green);
						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}else {
					if(JOptionPane.showConfirmDialog(null,"Voullez vous Supprimer le payement de mois Février ?","Confirmation",JOptionPane.OK_CANCEL_OPTION,3)==0) {
						try {
							removePayment(id,2);
							Fevrier.setBackground(new Color(148, 0, 211));
						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});
	//****************************
		Janvier = new JLabel("Janvier");
		Janvier.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Janvier.setHorizontalAlignment(SwingConstants.CENTER);
		Janvier.setForeground(new Color(255, 255, 255));
		Janvier.setBackground(new Color(148, 0, 211));
		Janvier.setFont(new Font("Tahoma", Font.BOLD, 14));
		Janvier.setBounds(75, 196, 73, 34);
		Janvier.setOpaque(true);
		
		if(mois.contains(1)) {
			Janvier.setBackground(Color.green);
		}
		contentPanel.add(Janvier);
		Janvier.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(Janvier.getBackground().getRGB()==new Color(148, 0, 211).getRGB()) {
					if(JOptionPane.showConfirmDialog(null,"Voullez vous ajouter le payement de mois Janvier ?","Confirmation",JOptionPane.OK_CANCEL_OPTION,3)==0) {
						try {
							addPayment(id,1);
							Janvier.setBackground(Color.green);
						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}else {
					if(JOptionPane.showConfirmDialog(null,"Voullez vous Supprimer le payement de mois Janvier ?","Confirmation",JOptionPane.OK_CANCEL_OPTION,3)==0) {
						try {
							removePayment(id,1);
							Janvier.setBackground(new Color(148, 0, 211));
						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});
	//****************************
		Avril = new JLabel("Avril");
		Avril.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Avril.setHorizontalAlignment(SwingConstants.CENTER);
		Avril.setForeground(new Color(255, 255, 255));
		Avril.setBackground(new Color(148, 0, 211));
		Avril.setFont(new Font("Tahoma", Font.BOLD, 14));
		Avril.setBounds(426, 196, 73, 34);
		Avril.setOpaque(true);
		if(mois.contains(4)) {
			Avril.setBackground(Color.green);
		}
		contentPanel.add(Avril);
		Avril.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(Avril.getBackground().getRGB()==new Color(148, 0, 211).getRGB()) {
					if(JOptionPane.showConfirmDialog(null,"Voullez vous ajouter le payement de mois Avril ?","Confirmation",JOptionPane.OK_CANCEL_OPTION,3)==0) {
						try {
							addPayment(id,4);
							Avril.setBackground(Color.green);
						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}else {
					if(JOptionPane.showConfirmDialog(null,"Voullez vous Supprimer le payement de mois Avril ?","Confirmation",JOptionPane.OK_CANCEL_OPTION,3)==0) {
						try {
							removePayment(id,4);
							Avril.setBackground(new Color(148, 0, 211));
						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});
	//****************************
		Juillet = new JLabel("Juillet");
		Juillet.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Juillet.setHorizontalAlignment(SwingConstants.CENTER);
		Juillet.setForeground(new Color(255, 255, 255));
		Juillet.setBackground(new Color(148, 0, 211));
		Juillet.setFont(new Font("Tahoma", Font.BOLD, 14));
		Juillet.setBounds(306, 265, 73, 34);
		Juillet.setOpaque(true);
		if(mois.contains(7)) {
			Juillet.setBackground(Color.green);
		}
		contentPanel.add(Juillet);
		Juillet.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(Juillet.getBackground().getRGB()==new Color(148, 0, 211).getRGB()) {
					if(JOptionPane.showConfirmDialog(null,"Voullez vous ajouter le payement de mois Juillet ?","Confirmation",JOptionPane.OK_CANCEL_OPTION,3)==0) {
						try {
							addPayment(id,7);
							Juillet.setBackground(Color.green);
						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}else {
					if(JOptionPane.showConfirmDialog(null,"Voullez vous Supprimer le payement de mois Juillet ?","Confirmation",JOptionPane.OK_CANCEL_OPTION,3)==0) {
						try {
							removePayment(id,7);
							Juillet.setBackground(new Color(148, 0, 211));
						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});
	//****************************
		Juin = new JLabel("Juin");
		Juin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Juin.setHorizontalAlignment(SwingConstants.CENTER);
		Juin.setForeground(new Color(255, 255, 255));
		Juin.setBackground(new Color(148, 0, 211));
		Juin.setFont(new Font("Tahoma", Font.BOLD, 14));
		Juin.setBounds(196, 265, 73, 34);
		Juin.setOpaque(true);
		if(mois.contains(6)) {
			Juin.setBackground(Color.green);
		}
		contentPanel.add(Juin);
		Juin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(Juin.getBackground().getRGB()==new Color(148, 0, 211).getRGB()) {
					if(JOptionPane.showConfirmDialog(null,"Voullez vous ajouter le payement de mois Juin ?","Confirmation",JOptionPane.OK_CANCEL_OPTION,3)==0) {
						try {
							addPayment(id,6);
							Juin.setBackground(Color.green);

						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}else {
					if(JOptionPane.showConfirmDialog(null,"Voullez vous Supprimer le payement de mois Juin ?","Confirmation",JOptionPane.OK_CANCEL_OPTION,3)==0) {
						try {
							removePayment(id,6);
							Juin.setBackground(new Color(148, 0, 211));

						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});
	//****************************
		Mai = new JLabel("Mai");
		Mai.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Mai.setHorizontalAlignment(SwingConstants.CENTER);
		Mai.setForeground(new Color(255, 255, 255));
		Mai.setBackground(new Color(148, 0, 211));
		Mai.setFont(new Font("Tahoma", Font.BOLD, 14));
		Mai.setBounds(75, 265, 73, 34);
		Mai.setOpaque(true);
		if(mois.contains(5)) {
			Mai.setBackground(Color.green);

		}
		contentPanel.add(Mai);
		Mai.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(Mai.getBackground().getRGB()==new Color(148, 0, 211).getRGB()) {
					if(JOptionPane.showConfirmDialog(null,"Voullez vous ajouter le payement de mois Mai ?","Confirmation",JOptionPane.OK_CANCEL_OPTION,3)==0) {
						try {
							addPayment(id,5);
							Mai.setBackground(Color.green);
						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}else {
					if(JOptionPane.showConfirmDialog(null,"Voullez vous Supprimer le payement de mois Mai ?","Confirmation",JOptionPane.OK_CANCEL_OPTION,3)==0) {
						try {
							removePayment(id,5);
							Mai.setBackground(new Color(148, 0, 211));
						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});
	//****************************
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\manal\\Desktop\\Cycle d'inginieurs GI\\2 eme annee\\S1\\Java\\projet\\interfaces_graphiques\\src\\res\\lycee50.png"));
		lblNewLabel.setBounds(83, 14, 77, 50);
		contentPanel.add(lblNewLabel);
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Payement.this.dispose();
			}
		});
		btnNewButton.setBackground(new Color(221, 160, 221));
		btnNewButton.setBounds(451, 414, 89, 23);
		contentPanel.add(btnNewButton);
	}



public void addPayment(int id,int mois) throws ClassNotFoundException, SQLException {
	
	String req="insert into payement (id_eleve,mois_paye) values(?,?)" ;
	
	ps=cnx.prepareStatement(req);
	ps.setInt(1,id);
	ps.setInt(2,mois);
	ps.execute();
	

	
}
public void removePayment(int id,int mois) throws ClassNotFoundException, SQLException {
	
	String req= "delete from payement where id_eleve=? and mois_paye= ?";
	ps=cnx.prepareStatement(req);
	ps.setInt(1, id);
	ps.setInt(2, mois);
	ps.execute();
	
	
}

}