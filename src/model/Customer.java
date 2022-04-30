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

    /**
     * creates an alert for when no item was selected
     */
    public static void itemSelectError(){

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("No Items Selected");
        alert.setContentText("Please select an item from the list!");
        alert.showAndWait();
    }

    /**
     * creates an alert for when the customer has appointments, therefore they cannot be deleted
     */
    public static void customerAppointmentError(){

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Customer has Appointments");
        alert.setContentText("You must delete all appointments for the customer before deleting the customer.");
        alert.showAndWait();
    }

    /**
     * creates an alert for when the customer has overlapping appointments
     */
    public static void customerOverlappingAppointments(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Overlapping Appointment");
        alert.setContentText("A customer cannot have overlapping appointments. Please choose a new appointment time!");
        alert.showAndWait();
    }

    /**
     * creates an alert for when the name is left empty
     */
    public static void emptyName() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("No Customer Name");
        alert.setContentText("Please enter a customer name.");
        alert.showAndWait();
    }

    /**
     * creates an alert for when the address is left empty
     */
    public static void emptyAddress() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("No Customer Address");
        alert.setContentText("Please enter a customer address.");
        alert.showAndWait();
    }

    /**
     * creates an alert for when the zip code is left empty
     */
    public static void emptyZip() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("No Customer Postal Code");
        alert.setContentText("Please enter a customer postal code.");
        alert.showAndWait();
    }

    /**
     * creates an alert for when the phone number is left empty
     */
    public static void emptyPhoneNumber() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("No Customer Phone Number");
        alert.setContentText("Please enter a customer phone number.");
        alert.showAndWait();
    }

}
