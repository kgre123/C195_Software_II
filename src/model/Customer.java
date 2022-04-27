package model;

import javafx.scene.control.Alert;

public class Customer {

    private int customerId;
    private String customerName;
    private String customerAddress;
    private String customerZip;
    private String customerPhone;
    private int customerDivisionId;
    private int customerCountryId;


    public Customer(){

    }

    public Customer(int customerId, String customerName, String customerAddress, String customerZip, String customerPhone, int customerDivisionId, int customerCountryId) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerZip = customerZip;
        this.customerPhone = customerPhone;
        this.customerDivisionId = customerDivisionId;
        this.customerCountryId = customerCountryId;

    }

    public Customer(String customerName, int customerId) {
        this.customerName = customerName;
        this.customerId = customerId;
    }

    /**
     * @return the customer name
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * @param customerName setter
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * @return the customer address
     */
    public String getCustomerAddress() {
        return customerAddress;
    }

    /**
     * @param customerAddress setter
     */
    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    /**
     * @return the customer zip
     */
    public String getCustomerZip() {
        return customerZip;
    }

    /**
     * @param customerZip setter
     */
    public void setCustomerZip(String customerZip) {
        this.customerZip = customerZip;
    }

    /**
     * @return the customer phone number
     */
    public String getCustomerPhone() {
        return customerPhone;
    }

    /**
     * @param customerPhone setter
     */
    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    /**
     * @return the customer id
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId setter
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * @return the customer division id
     */
    public int getCustomerDivisionId() {
        return customerDivisionId;
    }

    /**
     * @param customerDivisionId setter
     */
    public void setCustomerDivisionId(int customerDivisionId) {
        this.customerDivisionId = customerDivisionId;
    }

    /**
     * @return the customer country id
     */
    public int getCustomerCountryId() {
        return customerCountryId;
    }

    /**
     * @param customerCountryId setter
     */
    public void setCustomerCountryId(int customerCountryId) {
        this.customerCountryId = customerCountryId;
    }

    @Override
    public String toString() {
        return customerName;
    }

    public static void itemSelectError(){

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("No Items Selected");
        alert.setContentText("Please select an item from the list!");
        alert.showAndWait();
    }

    public static void customerAppointmentError(){

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Customer has Appointments");
        alert.setContentText("You must delete all appointments for the customer before deleting the customer.");
        alert.showAndWait();
    }
}
