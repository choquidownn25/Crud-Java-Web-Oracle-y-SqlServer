package conn;
import java.sql.Connection;
import java.sql.SQLException;
public class ConnectionUtils {
	 
    public static Connection getConnection() 
              throws ClassNotFoundException, SQLException {
 

    	// Aqu� estoy usando la base de datos Oracle.
    	// (Puede cambiar para usar otra base de datos).
        return OracleConnUtils.getOracleConnection();
         
        // return OracleConnUtils.getOracleConnection();
        // return MySQLConnUtils.getMySQLConnection();
        // return SQLServerConnUtils_JTDS.getSQLServerConnection_JTDS();
        // return SQLServerConnUtils_SQLJDBC.getSQLServerConnection_SQLJDBC();
        // return PostGresConnUtils.getPostGresConnection();
    }
     
    public static void closeQuietly(Connection conn) {
        try {
            conn.close();
        } catch (Exception e) {
        }
    }
 
    public static void rollbackQuietly(Connection conn) {
        try {
            conn.rollback();
        } catch (Exception e) {
        }
    }
}