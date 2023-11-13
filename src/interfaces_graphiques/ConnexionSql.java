package interfaces_graphiques;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConnexionSql {
Connection cn = null;
public static Connection connBD() throws SQLException, ClassNotFoundException {
	Class.forName("com.mysql.cj.jdbc.Driver");

	Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/lightingcandele","root","");

	return cn;
	
}

}
