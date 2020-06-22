package SignInProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
	private  Connection conn=null;
	static{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public  Connection createConnection()
	{
		try {
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/lms_db?serverTimezone=UTC","root","root");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public  void closeConnection()
	{
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
