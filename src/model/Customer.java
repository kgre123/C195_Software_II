package model;

public class Customer {

    private int customerId;
    private String customerName;
    private String customerAddress;
    private int customerZip;
    private int customerPhone;
    private int customerDivisionId;
    private int customerCountryId;


    public Customer(int customerId, String customerName, String customerAddress, int customerZip, int customerPhone, int customerDivisionId, int customerCountryId) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerZip = customerZip;
        this.customerPhone = customerPhone;
        this.customerDivisionId = customerDivisionId;
        this.customerCountryId = customerCountryId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public int getCustomerZip() {
        return customerZip;
    }

    public void setCustomerZip(int customerZip) {
        this.customerZip = customerZip;
    }

    public int getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(int customerPhone) {
        this.customerPhone = customerPhone;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getCustomerDivisionId() {
        return customerDivisionId;
    }

    public void setCustomerDivisionId(int customerDivisionId) {
        this.customerDivisionId = customerDivisionId;
    }

    public int getCustomerCountryId() {
        return customerCountryId;
    }

    public void setCustomerCountryId(int customerCountryId) {
        this.customerCountryId = customerCountryId;
    }
}
