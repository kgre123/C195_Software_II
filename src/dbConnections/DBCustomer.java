package dbConnections;

import helper.Conversions;
import model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DBCustomer {

    /**
     * This method creates an observable list of the customers
     * @return returns the observable list
     */
    public static ObservableList<Customer> getAllCustomers(){

        ObservableList<Customer> clist = FXCollections.observableArrayList();

        try{
            String sql = "SELECT customers.Customer_ID, customers.Customer_Name, customers.Address, customers.Postal_Code, customers.Phone, customers.Division_ID, first_level_divisions.Country_ID FROM customers INNER JOIN first_level_divisions ON customers.Division_ID=first_level_divisions.Division_ID";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int customerId = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                String customerAddress = rs.getString("Address");
                String customerZip = rs.getString("Postal_Code");
                String customerPhone = rs.getString("Phone");
                int customerDivisionId = rs.getInt("Division_ID");
                int customerCountryId = rs.getInt("Country_ID");
                Customer c = new Customer(customerId,customerName,customerAddress,customerZip,customerPhone,customerDivisionId,customerCountryId);

                clist.add(c);
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return clist;
    }

    /**
     * This method creates an observable list of the divisions
     * @param customer is the customer to add
     */
    public static void addCustomer(Customer customer) throws SQLException {

        try {
            String sql = "INSERT into customers (Customer_ID, Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) VALUES(NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setString(1, customer.getCustomerName());
            ps.setString(2, customer.getCustomerAddress());
            ps.setString(3, customer.getCustomerZip());
            ps.setString(4, customer.getCustomerPhone());
            ps.setTimestamp(5, Conversions.getCurrentTimestamp());
            ps.setString(6, DBUser.getCurrentUser());
            ps.setTimestamp(7, Conversions.getCurrentTimestamp());
            ps.setString(8, DBUser.getCurrentUser());
            ps.setInt(9, customer.getCustomerDivisionId());
            ps.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method updates a customer in the database
     * @param address the address to update
     * @param divisionId to be updated
     * @param id to be updated
     * @param name to be updated
     * @param phone to be updated
     * @param zip to be update
     */
    public static void updateCustomer(String name, String address, String zip, String phone, int divisionId, int id){

        try{

            String sql = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Last_Update = ?, Last_Updated_By = ?,  Division_ID = ? WHERE Customer_ID = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, address);
            ps.setString(3, zip);
            ps.setString(4, phone);
            ps.setTimestamp(5, Conversions.getCurrentTimestamp());
            ps.setString(6, DBUser.getCurrentUser());
            ps.setInt(7, divisionId);
            ps.setInt(8, id);

            ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    /**
     * This method deletes a customer in the database
     * @param customerId  the customer to be deleted
     */
    public static void deleteCustomer(int customerId){

        try{
            String sql = "DELETE FROM customers WHERE Customer_ID = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setInt(1, customerId);
            ps.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * This method creates an observable list of the customers
     * @return returns the observable list
     */
    public static ObservableList<Customer> getCustomersById() {

        ObservableList<Customer> clist = FXCollections.observableArrayList();

        try{
            String sql = "SELECT Customer_Name, Customer_ID FROM customers";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                String customerName = rs.getString("Customer_Name");
                int customerId = rs.getInt("Customer_ID");
                Customer c = new Customer(customerName, customerId);
                clist.add(c);
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return clist;
    }


}
