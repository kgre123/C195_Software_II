package model;

/**
 * @author Kyle Green
 * This is the country class file
 */
public class Country {

    private int countryId;
    private String countryName;

    public Country(int countryId, String countryName) {
        this.countryId = countryId;
        this.countryName = countryName;
    }

    /**
     * @return the country id
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * @param countryId setter
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    /**
     * @return the country name
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * @param countryName setter
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public String toString(){
        return(countryName);
    }
}
