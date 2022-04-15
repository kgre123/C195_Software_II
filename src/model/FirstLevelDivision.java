package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FirstLevelDivision {

    private static ObservableList<FirstLevelDivision> divisionIdList = FXCollections.observableArrayList();
    private int divisionID;
    private String divisionName;

    public FirstLevelDivision(int divisionID, String divisionName) {
        this.divisionID = divisionID;
        this.divisionName = divisionName;
    }

    public int getDivisionID() {
        return divisionID;
    }

    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public static void addDivision(FirstLevelDivision newDivision){
        divisionIdList.add(newDivision);
    }

    @Override
    public String toString(){
        return("ID: " + Integer.toString(divisionID) + " - Location: " + divisionName);
    }

}
