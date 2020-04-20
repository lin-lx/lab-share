package dbbean;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class ConnDb {
private Connection cn=null;
	
	public Connection getCon(){
		try{

			Class.forName("com.mysql.jdbc.Driver");   
			String username="root";
			String password="root";
			String url="jdbc:mysql://localhost:3308/lab";
			
			cn=DriverManager.getConnection(url,username,password);
			
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "error" +e.toString());
		}
		
	    return cn;
	}

}


