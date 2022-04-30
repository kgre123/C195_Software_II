package dbConnections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.FirstLevelDivision;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Kyle Green
 * This is the first level division connection to the database
 */
public class DBDivision {

    /**
     * This method creates an observable list of the divisions
     * @return returns the observable list
     */
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

    /**
     * This method selects the id and name of a division from the database
     * @param divisionId the divisionId that is being used as a parameter
     * @return returns the division id and name
     * @throws SQLException error
     */
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

    /**
     * This method returns the divisions based on the countryId
     * @param countryId the countryId is being referenced to return the divisions
     * @return returns the observable list
     * @throws SQLException error
     */
    public static ObservableList<FirstLevelDivision> returnDivisionByCountry(int countryId) throws SQLException {

        ObservableList<FirstLevelDivision> filter = FXCollections.observableArrayList();
        try {
            String sql = "SELECT Division_ID, Division FROM first_level_divisions WHERE Country_ID = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, countryId);
            ps.execute();

            ResultSet rs = ps.getResultSet();

            while (rs.next()) {
                int divisionId = rs.getInt("Division_ID");
                String name = rs.getString("Division");

                FirstLevelDivision f = new FirstLevelDivision(divisionId, name);
                filter.add(f);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return filter;
    }

}

