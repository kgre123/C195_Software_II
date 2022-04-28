package dbConnections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Country;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBCountry {

    /**
     * This method creates an observable list of the countries
     * @return returns the observable list
     */
    public static ObservableList<Country> getAllCountryIds(){

        ObservableList<Country> clist = FXCollections.observableArrayList();

        try{
            String sql = "SELECT Country_ID, Country FROM countries";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int countryId = rs.getInt("Country_ID");
                String countryName = rs.getString("Country");
                Country c = new Country(countryId,countryName);
                clist.add(c);
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return clist;
    }


    /**
     * This method gets a country by the specific id
     * @return returns the country
     * @param countryId the id to find the specific country
     */
    public static Country returnCountry(int countryId) throws SQLException {

        try {
            String sql = "SELECT Country_ID, Country FROM countries WHERE Country_ID = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setInt(1, countryId);
            ps.execute();

            ResultSet rs = ps.getResultSet();

            rs.next();
            int searchedCountryId = rs.getInt("Country_ID");
            String countryName = rs.getString("Country");
            Country c = new Country(searchedCountryId, countryName);
            return c;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

}

