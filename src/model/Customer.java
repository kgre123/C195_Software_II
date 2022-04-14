package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Customer {

    private static ObservableList<Customer> allCustomers = FXCollections.observableArrayList();

    private int customerId;
    private String customerName;
    private String customerAddress;
    private String customerZip;
    private String customerPhone;
    private int customerDivisionId;
    private int customerCountryId;


    public Customer(int customerId, String customerName, String customerAddress, String customerZip, String customerPhone, int customerDivisionId) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerZip = customerZip;
        this.customerPhone = customerPhone;
        this.customerDivisionId = customerDivisionId;

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

    /**
     * adding a customer to the observable list
     * @param newCustomer specifies the customer being added
     */
    public static void addCustomer(Customer newCustomer) {
        allCustomers.add(newCustomer);
    }

    /**
     * deleting a customer from the observable list
     * @param selectedCustomer the customer that is being deleted
     * @return deletes the customer if found
     */
    public static boolean deleteCustomer(Customer selectedCustomer) {
        if(allCustomers.contains(selectedCustomer)){
            allCustomers.remove(selectedCustomer);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * finding the index of the customers
     * @param customerId the customer id being searched for
     * @return index
     */
    public static int getCustomerIndex(int customerId){

        for (int i = 0; i < allCustomers.size(); i++) {
            if (allCustomers.get(i).getCustomerId() == customerId) {
                return i;
            }
        }
        return 0;
    }

    /**
     * updating the customer to not create duplicates
     * @param index index of the customer
     * @param selectedCustomer the customer that is being searched for
     */
    public static void updateCustomer(int index, Customer selectedCustomer) {

        for (int i = 0; i < allCustomers.size(); i++) {
            if (allCustomers.get(i).getCustomerId() == selectedCustomer.getCustomerId()) {
                allCustomers.set(i, selectedCustomer);
                break;
            }
        }
    }



}
