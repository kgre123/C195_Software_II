package dbConnections;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Kyle Green
 * This is the user connection to the database
 */
public class DBUser {

    static String currentUser;

    /**
     * This method collects the username and password for login
     * @param username the username checked for login
     * @param password the password checked for login
     * @return boolean value of the login
     */
    public static boolean userLogin(String username, String password){

        try{
            String sql = "SELECT * FROM users WHERE User_Name = ? AND Password = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);


            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                setCurrentUser(username);
                return true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    /**
     * This method sets the current username for the log file to check
     * @param username the username that is being set
     */
    private static void setCurrentUser(String username) {

        currentUser = username;
    }

    /**
     * This method sets the current username for the log file to check
     * @return returns the username currently logged in
     */
    public static String getCurrentUser(){
        return currentUser;
    }


}
