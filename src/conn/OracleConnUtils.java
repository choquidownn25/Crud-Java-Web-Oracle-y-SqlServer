package conn;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleConnUtils {
	  
	   public static Connection getOracleConnection()
	           throws ClassNotFoundException, SQLException {
	        
	       // Note: Change the connection parameters accordingly.
	       String hostName = "localhost";
	       String sid = "xe";
	       String userName = "system";
	       String password = "12345";
	  
	       return getOracleConnection(hostName, sid, userName, password);
	   }
	  
	   public static Connection getOracleConnection(String hostName, String sid,
	           String userName, String password) throws ClassNotFoundException,
	           SQLException {
	   
	       Class.forName("oracle.jdbc.driver.OracleDriver");
	  
	       // URL Conecion para Oracle
	       // Example: 
	       // jdbc:oracle:thin:@localhost:1521:db11g
	       // jdbc:oracle:thin:@//HOSTNAME:PORT/SERVICENAME
	       String connectionURL = "jdbc:oracle:thin:@" + hostName + ":1521:" + sid;
	  
	       Connection conn = DriverManager.getConnection(connectionURL, userName,
	               password);
	       return conn;
	   }
	}