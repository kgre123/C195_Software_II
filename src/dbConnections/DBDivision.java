package dbConnections;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.FirstLevelDivision;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBDivision {

    public static ObservableList<FirstLevelDivision> getAllDivisionIds(){

        ObservableList<FirstLevelDivision> dlist = FXCollections.observableArrayList();

        try{
            String sql = "SELECT Division_ID, Division FROM first_level_divisions";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int divisionId = rs.getInt("Division_ID");
                String divisionName = rs.getString("Division");
                FirstLevelDivision d = new FirstLevelDivision(divisionId,divisionName);
                dlist.add(d);
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return dlist;
    }

    public static FirstLevelDivision returnDivision(int divisionId) throws SQLException {

        try {
            String sql = "SELECT Division_ID, Division FROM first_level_divisions WHERE Division_ID = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, divisionId);
            ps.execute();

            ResultSet rs = ps.getResultSet();

            rs.next();
            int searchedDivisionId = rs.getInt("Division_ID");
            String divisionName = rs.getString("Division");
            FirstLevelDivision f = new FirstLevelDivision(searchedDivisionId, divisionName);
            return f;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

}

