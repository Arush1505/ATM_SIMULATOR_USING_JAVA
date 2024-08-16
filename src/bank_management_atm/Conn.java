package bank_management_atm;
import java.sql.*;
public class Conn {
Statement s;

Connection co;
	public Conn() {
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		co=DriverManager.getConnection("jdbc:mysql://localhost:3306/bankmanagementsystem","root","admin");        // bankmanagementsystem database
		s=co.createStatement();	
	}
	catch (Exception e) {
		System.out.println(e);
	}
	
	
//    public Connection getConnection() {
//        return co;
//    }
}
	
	
	
}