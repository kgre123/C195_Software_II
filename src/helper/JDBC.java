package helper;

import model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {

    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String location = "//localhost/";
    //private static final String ipAddress = "//wgudb.ucertify.com:3306/";
    private static final String databaseName = "client_schedule";

    //private static final String jdbcURL = protocol + vendor + ipAddress + databaseName;
    private static final String jdbcURL = protocol + vendor + location + databaseName + "?connectionTimeZone=SERVER";
    private static final String driver = "com.mysql.cj.jdbc.Driver";

    private static final String username = "sqlUser";
    private static final String password = "Passw0rd!";

    private static Connection conn = null;

    public static Connection startConnection() {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(jdbcURL,username,password);
            System.out.println("Connection Successful");
        }
        catch(SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static Connection getConnection(){
        return conn;
    }

    public static void closeConnection() {
        try {
            conn.close();
            System.out.println("Connection closed!");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }


}
