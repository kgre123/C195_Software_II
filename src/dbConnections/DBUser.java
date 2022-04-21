package dbConnections;

import helper.JDBC;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUser {

    public static boolean userLogin(String username, String password){

        try{
            String sql = "SELECT * FROM users WHERE User_Name = ? AND Password = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);


            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                return true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }


}
