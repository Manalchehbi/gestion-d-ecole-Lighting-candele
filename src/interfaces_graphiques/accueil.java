package interfaces_graphiques;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JDesktopPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class accueil extends JFrame {

	private JPanel contentPane;
	
	/**
	 * Launch the application.
	 */
	private accuil accuil;
	private creche creche;
	private Primaire Primaire;
	private College College;
	private  Lycee Lycee;
	private Professeurs Professeurs;
	private Statistique Statistique; 
	private String dir=System.getProperty("user.dir");
	private Color active=new Color(0, 206, 209);
	
	JPanel pan_main;
	
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					accueil frame = new accueil();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public accueil() throws ClassNotFoundException, SQLException {
		
		setBackground(new Color(216, 191, 216));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 880, 717);
		this.setResizable(false);
		this.setDefaultCloseOperation(0);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(216, 191, 216));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelhome = new JPanel();
		JPanel panelcreche = new JPanel();
		JPanel panelprmaire = new JPanel();
		JPanel panelcollege = new JPanel();
		JPanel panellycee = new JPanel();
		JPanel panelprofesseur = new JPanel();
		JPanel panelstati = new JPanel();
		
		
		
		
		
		 accuil = new accuil();
		
		 creche = new creche();
		 
		 Primaire = new Primaire();
		 
		 College = new College();
		 
		  Lycee = new Lycee();
	
		 Professeurs = new  Professeurs();
		 
		 Statistique = new Statistique();
		
		
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(75, 0, 130));
		panel.setBounds(0, 0, 864, 46);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Administration Centre Lighting Candele ");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Verdana", Font.BOLD, 20));
		lblNewLabel_3.setForeground(new Color(240, 255, 240));
		lblNewLabel_3.setBounds(202, 5, 487, 30);
		panel.add(lblNewLabel_3);
		
		JButton btnNewButton = new JButton("D\u00E9connecter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "etes vous sure de vouloir déconnecter ?") == 0) {
					 JFrame frame;
						
							
							
						
					accueil.this.dispose();
				}

			}
		});
	
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setForeground(new Color(248, 248, 255));
		btnNewButton.setBackground(new Color(220, 20, 60));
		btnNewButton.setBounds(699, 13, 139, 23);
		panel.add(btnNewButton);
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(138, 43, 226));
		panel_1.setBounds(0, 46, 175, 632);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		
		panelhome.addMouseListener(new PanellButtonMouseAdaptter(panelhome){
			@Override
			public void mouseClicked(MouseEvent e) {
				panelhome.setBackground(active);
				panelcreche.setBackground(new Color(204, 153, 204));
				panelprmaire.setBackground(new Color(204, 153, 204));
				panelcollege.setBackground(new Color(204, 153, 204));
				panellycee.setBackground(new Color(204, 153, 204));
				panelprofesseur.setBackground(new Color(204, 153, 204));
				panelstati.setBackground(new Color(204, 153, 204));
			
				pan_main.add(accuil);
				menuClicked(accuil);
				lblNewLabel_3.setText("Administration Lighting Candele ");
			}
		});
		panelhome.setBorder(null);
		panelhome.setBackground(new Color(204, 153, 204));
		panelhome.setBounds(0, 11, 175, 47);
		panel_1.add(panelhome);
		panelhome.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(0, 191, 255));
		panel_3.setBounds(0, 0, 15, 48);
		panelhome.add(panel_3);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(25, 0, 50, 48);
		panelhome.add(lblNewLabel_1);
		lblNewLabel_1.setIcon(new ImageIcon(dir+"\\src\\res\\hom50.png"));
		
		JLabel lblNewLabel_2 = new JLabel("Accueil");
		lblNewLabel_2.setForeground(new Color(255, 250, 250));
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel_2.setBounds(89, 11, 76, 26);
		panelhome.add(lblNewLabel_2);
		
		
		panelcreche.addMouseListener(new PanellButtonMouseAdaptter(panelcreche){
			@Override
			public void mouseClicked(MouseEvent e) {
				panelhome.setBackground(new Color(204, 153, 204));
				panelcreche.setBackground(active);
				panelprmaire.setBackground(new Color(204, 153, 204));
				panelcollege.setBackground(new Color(204, 153, 204));
				panellycee.setBackground(new Color(204, 153, 204));
				panelprofesseur.setBackground(new Color(204, 153, 204));
				panelstati.setBackground(new Color(204, 153, 204));
			
				pan_main.add(creche);
				menuClicked(creche);
				lblNewLabel_3.setText("Espace de Cr\u00E8che ");
			}
		});
		panelcreche.setLayout(null);
		panelcreche.setBorder(null);
		panelcreche.setBackground(new Color(204, 153, 204));
		panelcreche.setBounds(0, 90, 175, 56);
		panel_1.add(panelcreche);
		
		JPanel panel_3_1 = new JPanel();
		panel_3_1.setBackground(new Color(0, 206, 209));
		panel_3_1.setBounds(0, 0, 15, 60);
		panelcreche.add(panel_3_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("Cr\u00E8che");
		lblNewLabel_2_1.setBackground(new Color(64, 224, 208));
		lblNewLabel_2_1.setForeground(new Color(255, 250, 250));
		lblNewLabel_2_1.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel_2_1.setBounds(89, 26, 76, 26);
		panelcreche.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("");
		lblNewLabel_1_2.setIcon(new ImageIcon(dir+"\\src\\res\\crech50.png"));
		lblNewLabel_1_2.setBounds(25, 0, 54, 52);
		panelcreche.add(lblNewLabel_1_2);
		
		
		panelprmaire.addMouseListener(new PanellButtonMouseAdaptter(panelprmaire){
			@Override
			public void mouseClicked(MouseEvent e) {
				panelhome.setBackground(new Color(204, 153, 204));
				panelcreche.setBackground(new Color(204, 153, 204));
				panelprmaire.setBackground(active);
				panelcollege.setBackground(new Color(204, 153, 204));
				panellycee.setBackground(new Color(204, 153, 204));
				panelprofesseur.setBackground(new Color(204, 153, 204));
				panelstati.setBackground(new Color(204, 153, 204));
				
				pan_main.add(Primaire);
				menuClicked(Primaire);
				lblNewLabel_3.setText("Espace de Primaire ");
			}
		});
		panelprmaire.setLayout(null);
		panelprmaire.setBorder(null);
		panelprmaire.setBackground(new Color(204, 153, 204));
		panelprmaire.setBounds(0, 175, 175, 56);
		panel_1.add(panelprmaire);
		
		JPanel panel_3_1_1 = new JPanel();
		panel_3_1_1.setBackground(new Color(0, 206, 209));
		panel_3_1_1.setBounds(0, 0, 15, 60);
		panelprmaire.add(panel_3_1_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Primaire");
		lblNewLabel_2_1_1.setForeground(new Color(255, 250, 250));
		lblNewLabel_2_1_1.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel_2_1_1.setBackground(new Color(64, 224, 208));
		lblNewLabel_2_1_1.setBounds(89, 26, 76, 26);
		panelprmaire.add(lblNewLabel_2_1_1);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("");
		lblNewLabel_1_2_1.setIcon(new ImageIcon(dir+"\\src\\res\\primaire50.png"));
		lblNewLabel_1_2_1.setBounds(25, 0, 54, 52);
		panelprmaire.add(lblNewLabel_1_2_1);
		
		panelcollege.addMouseListener(new PanellButtonMouseAdaptter(panelcollege){
			@Override
			public void mouseClicked(MouseEvent e) {
				panelhome.setBackground(new Color(204, 153, 204));
				panelcreche.setBackground(new Color(204, 153, 204));
				panelprmaire.setBackground(new Color(204, 153, 204));
				panelcollege.setBackground(active);
				panellycee.setBackground(new Color(204, 153, 204));
				panelprofesseur.setBackground(new Color(204, 153, 204));
				panelstati.setBackground(new Color(204, 153, 204));
				
				pan_main.add(College);
				menuClicked(College);
				lblNewLabel_3.setText("Espace de Coll\u00E9ge ");
				
			}
		});
		panelcollege.setLayout(null);
		panelcollege.setBorder(null);
		panelcollege.setBackground(new Color(204, 153, 204));
		panelcollege.setBounds(0, 260, 175, 56);
		panel_1.add(panelcollege);
		
		JPanel panel_3_1_1_1 = new JPanel();
		panel_3_1_1_1.setBackground(new Color(0, 206, 209));
		panel_3_1_1_1.setBounds(0, 0, 15, 60);
		panelcollege.add(panel_3_1_1_1);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("Coll\u00E9ge");
		lblNewLabel_2_1_1_1.setForeground(new Color(255, 250, 250));
		lblNewLabel_2_1_1_1.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel_2_1_1_1.setBackground(new Color(64, 224, 208));
		lblNewLabel_2_1_1_1.setBounds(89, 26, 76, 26);
		panelcollege.add(lblNewLabel_2_1_1_1);
		
		JLabel lblNewLabel_1_2_1_1 = new JLabel("");
		lblNewLabel_1_2_1_1.setIcon(new ImageIcon(dir+"\\src\\res\\collecge50.png"));
		lblNewLabel_1_2_1_1.setBounds(25, 0, 54, 52);
		panelcollege.add(lblNewLabel_1_2_1_1);
		
		
		panellycee.addMouseListener(new PanellButtonMouseAdaptter(panellycee){
			@Override
			public void mouseClicked(MouseEvent e) {
				panelhome.setBackground(new Color(204, 153, 204));
				panelcreche.setBackground(new Color(204, 153, 204));
				panelprmaire.setBackground(new Color(204, 153, 204));
				panelcollege.setBackground(new Color(204, 153, 204));
				panellycee.setBackground(active);
				panelprofesseur.setBackground(new Color(204, 153, 204));
				panelstati.setBackground(new Color(204, 153, 204));
				
				pan_main.add(Lycee);
				menuClicked(Lycee);
				lblNewLabel_3.setText("Espace de Lyc\u00E9e ");
			}
		});
		panellycee.setLayout(null);
		panellycee.setBorder(null);
		panellycee.setBackground(new Color(204, 153, 204));
		panellycee.setBounds(0, 351, 175, 56);
		panel_1.add(panellycee);
		
		JPanel panel_3_1_1_2 = new JPanel();
		panel_3_1_1_2.setBackground(new Color(0, 206, 209));
		panel_3_1_1_2.setBounds(0, 0, 15, 60);
		panellycee.add(panel_3_1_1_2);
		
		JLabel lblNewLabel_2_1_1_2 = new JLabel("Lyc\u00E9e");
		lblNewLabel_2_1_1_2.setForeground(new Color(255, 250, 250));
		lblNewLabel_2_1_1_2.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel_2_1_1_2.setBackground(new Color(64, 224, 208));
		lblNewLabel_2_1_1_2.setBounds(89, 26, 76, 26);
		panellycee.add(lblNewLabel_2_1_1_2);
		
		JLabel lblNewLabel_1_2_1_2 = new JLabel("");
		lblNewLabel_1_2_1_2.setIcon(new ImageIcon(dir+"\\src\\res\\lycee50.png"));
		lblNewLabel_1_2_1_2.setBounds(25, 0, 54, 52);
		panellycee.add(lblNewLabel_1_2_1_2);
		
		
		panelprofesseur.addMouseListener(new PanellButtonMouseAdaptter(panelprofesseur){
			@Override
			public void mouseClicked(MouseEvent e) {
				panelhome.setBackground(new Color(204, 153, 204));
				panelcreche.setBackground(new Color(204, 153, 204));
				panelprmaire.setBackground(new Color(204, 153, 204));
				panelcollege.setBackground(new Color(204, 153, 204));
				panellycee.setBackground(new Color(204, 153, 204));
				panelprofesseur.setBackground(active);
				panelstati.setBackground(new Color(204, 153, 204));
				
				pan_main.add(Professeurs);
				menuClicked(Professeurs);
				lblNewLabel_3.setText("Espace de Professeurs ");
			}
		});
		panelprofesseur.setLayout(null);
		panelprofesseur.setBorder(null);
		panelprofesseur.setBackground(new Color(204, 153, 204));
		panelprofesseur.setBounds(0, 437, 175, 56);
		panel_1.add(panelprofesseur);
		
		JPanel panel_3_1_1_3 = new JPanel();
		panel_3_1_1_3.setBackground(new Color(0, 206, 209));
		panel_3_1_1_3.setBounds(0, 0, 15, 60);
		panelprofesseur.add(panel_3_1_1_3);
		
		JLabel lblNewLabel_2_1_1_3 = new JLabel("Professeurs");
		lblNewLabel_2_1_1_3.setForeground(new Color(255, 250, 250));
		lblNewLabel_2_1_1_3.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel_2_1_1_3.setBackground(new Color(64, 224, 208));
		lblNewLabel_2_1_1_3.setBounds(70, 26, 105, 26);
		panelprofesseur.add(lblNewLabel_2_1_1_3);
		
		JLabel lblNewLabel_1_2_1_3 = new JLabel("");
		lblNewLabel_1_2_1_3.setIcon(new ImageIcon(dir+"\\src\\res\\teacher50.png"));
		lblNewLabel_1_2_1_3.setBounds(25, 0, 54, 52);
		panelprofesseur.add(lblNewLabel_1_2_1_3);
		
		
		panelstati.addMouseListener(new PanellButtonMouseAdaptter(panelstati){
			@Override
			public void mouseClicked(MouseEvent e) {
				panelhome.setBackground(new Color(204, 153, 204));
				panelcreche.setBackground(new Color(204, 153, 204));
				panelprmaire.setBackground(new Color(204, 153, 204));
				panelcollege.setBackground(new Color(204, 153, 204));
				panellycee.setBackground(new Color(204, 153, 204));
				panelprofesseur.setBackground(new Color(204, 153, 204));
				panelstati.setBackground(active);
//				try {
//					Statistique=new Statistique();
//				} catch (ClassNotFoundException | SQLException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
				pan_main.add(Statistique);
				menuClicked(Statistique);
				
			}
		});
		panelstati.setLayout(null);
		panelstati.setBorder(null);
		panelstati.setBackground(new Color(204, 153, 204));
		panelstati.setBounds(0, 523, 175, 56);
		panel_1.add(panelstati);
		
		JPanel panel_3_1_1_4 = new JPanel();
		panel_3_1_1_4.setBackground(new Color(0, 206, 209));
		panel_3_1_1_4.setBounds(0, 0, 15, 60);
		panelstati.add(panel_3_1_1_4);
		
		JLabel lblNewLabel_2_1_1_4 = new JLabel("Statistiques");
		lblNewLabel_2_1_1_4.setForeground(new Color(255, 250, 250));
		lblNewLabel_2_1_1_4.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel_2_1_1_4.setBackground(new Color(64, 224, 208));
		lblNewLabel_2_1_1_4.setBounds(77, 26, 88, 26);
		panelstati.add(lblNewLabel_2_1_1_4);
		
		JLabel lblNewLabel_1_2_1_4 = new JLabel("");
		lblNewLabel_1_2_1_4.setIcon(new ImageIcon(dir+"\\src\\res\\bus50.png"));
		lblNewLabel_1_2_1_4.setBounds(25, 0, 54, 52);
		panelstati.add(lblNewLabel_1_2_1_4);
		
		
		pan_main = new JPanel();
		
		pan_main.setBounds(175, 49, 689, 643);
		
		contentPane.add(pan_main);
		pan_main.setLayout(null);
		pan_main.add(Statistique);
		pan_main.add(accuil);
		
		menuClicked(accuil);
		
	
		
		
	}
	
	public void menuClicked(JPanel panel) {
		 accuil.setVisible(false);
		 creche.setVisible(false);
		 Primaire.setVisible(false);
		 College.setVisible(false);
		  Lycee.setVisible(false);
		 Professeurs.setVisible(false);
		 Statistique.setVisible(false);
		 panel.setVisible(true);
	}

	public class PanellButtonMouseAdaptter extends MouseAdapter {
		JPanel pannel ;
			public PanellButtonMouseAdaptter(JPanel pannel) {
				this.pannel = pannel;
			}
			@Override 
			public void mouseEntered(MouseEvent e) {
				if(pannel.getBackground()!=active)
				pannel.setBackground(new Color(123, 182, 174));
			}
			@Override 
			public void mouseExited(MouseEvent e) {
				if(pannel.getBackground()!=active)
				pannel.setBackground(new Color(204,153,204));
			}
			@Override 
			public void mousePressed(MouseEvent e) {
				if(pannel.getBackground()!=active)
				pannel.setBackground(new Color(204,153,204));
			}
			// mauve clair 204,153,204
			// mauve fanc� 75,0,130
			@Override 
			public void mouseReleased(MouseEvent e) {
				if(pannel.getBackground()!=active)
				pannel.setBackground(new Color(75,0,130));
			}
		
	}
}
