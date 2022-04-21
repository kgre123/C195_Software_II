package model;

public class FirstLevelDivision {

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

    public static int divisionToCountryID(int divisionID){

        int countryID;
        if (divisionID > 100){
            countryID = 2;
        }
        else if (divisionID < 60){
            countryID = 1;
        }
        else {
            countryID = 3;
        }
        return countryID;
    }


    @Override
    public String toString(){
        return("ID: " + Integer.toString(divisionID) + " - Location: " + divisionName);
    }

}
