package interfaces_graphiques;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;

public class LoginFrame {

	JFrame frame;
	
	private Image ic_logo = new ImageIcon(LoginFrame.class.getResource("")).getImage().getScaledInstance(160, 160, Image.SCALE_SMOOTH);
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private String dir=System.getProperty("user.dir");
	private int x, y;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame window = new LoginFrame();
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		Color mytop = new Color(255, 255, 255);
		Color mymid = new Color(125,64,150);
		
		GradientPanel contentPanel = new GradientPanel(Color.decode("#a883b8"), Color.decode("#643c74"),GradientPanel.DIAGONAL_DOWN);
		contentPanel.setArc(10);
		contentPanel.setBorderColor(Color.white);
		contentPanel.setBorderWidth(2);
		contentPanel.setBounds(200, 50, 500, 300);
		frame.getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(229, 87, 250, 50);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		txtUsername = new JTextField();
		txtUsername.setBackground(Color.WHITE);
		txtUsername.setBounds(10, 11, 230, 28);
		panel.add(txtUsername);
		txtUsername.setColumns(10);
		txtUsername.setBorder(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(229, 173, 250, 50);
		contentPanel.add(panel_1);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(10, 11, 230, 28);
		panel_1.add(txtPassword);
		txtPassword.setBorder(null);
		
		JLabel lblNewLabel_2 = new JLabel("Login");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setBounds(229, 62, 250, 14);
		contentPanel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Mot de passe");
		lblNewLabel_2_1.setForeground(Color.WHITE);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2_1.setBounds(229, 148, 250, 14);
		contentPanel.add(lblNewLabel_2_1);
		
		JButton btnNewButton = new JButton("Connexion");
		btnNewButton.setForeground(new Color(148, 0, 211));
		btnNewButton.setBackground(new Color(216, 191, 216));
		btnNewButton.setBounds(229, 234, 250, 43);
		contentPanel.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
		String login = txtUsername.getText().toString();
		String mp = txtPassword.getText().toString();
		if(login.equals("") ||  mp.equals("")){
			JOptionPane.showMessageDialog(null, "Vueillez remplir tous les champs ! ");
		}else if ((login.equals("manal") &&  mp.equals("manal")) || (login.equals("brahim") &&  mp.equals("brahim")) ||(login.equals("admin") &&  mp.equals("admin"))){
			accueil window;
			try {
				window = new accueil();
				window.setVisible(true);
				frame.dispose();
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}else {
			JOptionPane.showMessageDialog(null, "Le Login ou le Mot de passe est incorr\u00E9cte !");
			txtUsername.setText("");
			txtPassword.setText("");
		}
		}
	});
		
		JLabel lblNewLabel_3 = new JLabel("LOGIN");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_3.setBounds(229, 25, 115, 14);
		contentPanel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("X");
		lblNewLabel_3_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
					System.exit(0);
			}
		});
		lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1.setForeground(Color.WHITE);
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_3_1.setBounds(469, 11, 21, 28);
		contentPanel.add(lblNewLabel_3_1);
		
		GradientPanel circlePanel_1 = new GradientPanel(Color.WHITE, new Color(125, 64, 150));
		circlePanel_1.setLayout(null);
		circlePanel_1.setBorderWidth(2);
		circlePanel_1.setBorderColor(Color.WHITE);
		circlePanel_1.setArc(400);
		circlePanel_1.setBounds(0, 0, 400, 400);
		frame.getContentPane().add(circlePanel_1);
		
		JLabel lblLogo_1 = new JLabel("");
		lblLogo_1.setIcon(new ImageIcon(dir+"\\src\\res\\22.png"));
		lblLogo_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo_1.setBounds(66, 45, 244, 215);
		circlePanel_1.add(lblLogo_1);
		
		JLabel lblNewLabel_4 = new JLabel("CENTRE");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_4.setBounds(76, 256, 234, 36);
		circlePanel_1.add(lblNewLabel_4);
		
		JLabel lblNewLabel_1_1 = new JLabel("Lighting Cendele");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(76, 304, 234, 36);
		circlePanel_1.add(lblNewLabel_1_1);
		
	}
}
