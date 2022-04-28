package model;

public class FirstLevelDivision {

    private int divisionID;
    private String divisionName;

    public FirstLevelDivision(int divisionID, String divisionName) {
        this.divisionID = divisionID;
        this.divisionName = divisionName;
    }

    /**
     * @return the division id
     */
    public int getDivisionID() {
        return divisionID;
    }

    /**
     * @param divisionID setter
     */
    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }

    /**
     * @return the division name
     */
    public String getDivisionName() {
        return divisionName;
    }

    /**
     * @param divisionName setter
     */
    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    @Override
    public String toString(){
        return(divisionName);
    }

}
