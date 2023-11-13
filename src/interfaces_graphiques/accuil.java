package interfaces_graphiques;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;

public class accuil extends JPanel {

	/**
	 * Create the panel.
	 */
	private String dir=System.getProperty("user.dir");
	
	public accuil() {
		setForeground(new Color(221, 160, 221));
		setBackground(new Color(221, 160, 221));
		setBounds(0, 0, 689, 643);
		setLayout(null);
		setVisible(true);
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(dir+"\\src\\res\\ic_logo.png"));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 53));
		lblNewLabel.setBounds(94, 0, 643, 643);
		add(lblNewLabel);
	}

}
