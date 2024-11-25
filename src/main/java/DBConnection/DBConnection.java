
package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {
    private static final String urlConnect = "jdbc:sqlserver://localhost:1433;databasename=SWP_EDUNEXT_COURSERA;user=HUYBER;password=090703;characterEncoding=UTF-8;encrypt=true;trustServerCertificate=true;";
    public static Connection connect() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            // tao doi tuong connection 
            Connection conn = DriverManager.getConnection(urlConnect);
            return conn;
        } catch(ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        } 
        return null;
    }
    
    public static void main(String args[]) {
        System.out.println(connect());
    }
}
