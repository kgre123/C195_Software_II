package dbConnections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contact;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Kyle Green
 * This is the contact connection to the database
 */
public class DBContact {

    /**
     * This method creates an observable list of the contacts
     * @return returns the observable list
     */
    public static ObservableList<Contact> getAllContacts() {

        ObservableList<Contact> clist = FXCollections.observableArrayList();

        try {
            String sql = "SELECT Contact_ID, Contact_Name FROM contacts";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int contactId = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");
                Contact c = new Contact(contactId, contactName);
                clist.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clist;
    }

    /**
     * This method returns a contact based on the contact id
     * @return returns the contact
     * @param contactId the contact to be found
     * @throws SQLException error
     */
    public static Contact returnContact(int contactId) throws SQLException {

        try {
            String sql = "SELECT Contact_ID, Contact_Name FROM contacts WHERE Contact_ID = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setInt(1, contactId);
            ps.execute();

            ResultSet rs = ps.getResultSet();

            rs.next();
            int searchedContactId = rs.getInt("Contact_ID");
            String contactName = rs.getString("Contact_Name");
            Contact c = new Contact(searchedContactId, contactName);
            return c;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }
}
