package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Country {

    private static ObservableList<Country> countryIdList = FXCollections.observableArrayList();

    private int countryId;
    private String countryName;

    public Country(int countryId, String countryName) {
        this.countryId = countryId;
        this.countryName = countryName;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public static void addCountry(Country newCountry){
        countryIdList.add(newCountry);
    }

    @Override
    public String toString(){
        return("ID: " + Integer.toString(countryId) + " - Country: " + countryName);
    }
}
