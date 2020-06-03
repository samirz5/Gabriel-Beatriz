package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    //todo implement ssl
    private static String host = "jdbc:mysql://studmysql01.fhict.local:3306/dbi333996";
    private static String username = "dbi333996";
    private static String password = "Gabriel&Beatriz";
    private static Connection con;

    public static Connection getConnection() {
        try {
             con = DriverManager.getConnection(host, username, password);

        } catch (SQLException ex) {
            System.out.println("Driver not found.");
        }

        return con;
    }


}
