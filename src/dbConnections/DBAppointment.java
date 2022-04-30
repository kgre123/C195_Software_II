package dbConnections;

import helper.Conversions;
import helper.Log;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class DBAppointment {

    /**
     * This method creates an observable list of all weekly appointments
     * @return returns a list of appointments for the current week
     */
    public static ObservableList<Appointment> getAllWeeklyAppointments() {

        ObservableList<Appointment> alist = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * from appointments WHERE YEARWEEK(start) = YEARWEEK(NOW())";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                Timestamp startDate = rs.getTimestamp("Start");
                Timestamp endDate = rs.getTimestamp("End");
                int customerId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");
                int contactId = rs.getInt("Contact_ID");
                Appointment a = new Appointment(appointmentId, title, description, location, type, startDate, endDate, customerId, userId, contactId);

                alist.add(a);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alist;
    }

    /**
     * This method creates an observable list of all monthly appointments
     * @return returns the list of appointments for the current month
     */
    public static ObservableList<Appointment> getAllMonthlyAppointments() {

        ObservableList<Appointment> alist = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * from appointments WHERE MONTH(start) = MONTH(NOW()) AND YEAR(start) = YEAR(NOW())";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                Timestamp startDate = rs.getTimestamp("Start");
                Timestamp endDate = rs.getTimestamp("End");
                int customerId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");
                int contactId = rs.getInt("Contact_ID");
                Appointment a = new Appointment(appointmentId, title, description, location, type, startDate, endDate, customerId, userId, contactId);

                alist.add(a);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alist;
    }

    /**
     * This method creates an appointment in the database
     * @param appointment the appointment that is being created
     */
    public static void addAppointment(Appointment appointment) {

        try {
            String sql = "INSERT into appointments (Appointment_ID, Title, Description, Location, Type, Start, End, Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID) VALUES(NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setString(1, appointment.getTitle());
            ps.setString(2, appointment.getDescription());
            ps.setString(3, appointment.getLocation());
            ps.setString(4, appointment.getType());
            ps.setTimestamp(5, appointment.getStartDate());
            ps.setTimestamp(6, appointment.getEndDate());
            ps.setTimestamp(7, Conversions.getCurrentTimestamp());
            ps.setString(8, DBUser.getCurrentUser());
            ps.setTimestamp(9, Conversions.getCurrentTimestamp());
            ps.setString(10, DBUser.getCurrentUser());
            ps.setInt(11, appointment.getCustomerId());
            ps.setInt(12, appointment.getUserId());
            ps.setInt(13, appointment.getContactId());
            ps.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * This method updates an appointment in the database
     * @param contactId the contactId to add
     * @param title the title to add
     * @param description the description to add
     * @param location the location to add
     * @param type the type to add
     * @param start the start date to add
     * @param end the end date to add
     * @param customerId the customerId to add
     * @param userId the userId to add
     * @param id the appointmentId to search by
     */
    public static void updateAppointment(String title, String description, String location, String type, Timestamp start, Timestamp end, int customerId, int userId, int contactId, int id) {

        try {
            String sql = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Last_Update = ?, Last_Updated_By = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setString(1, title);
            ps.setString(2, description);
            ps.setString(3, location);
            ps.setString(4, type);
            ps.setTimestamp(5, start);
            ps.setTimestamp(6, end);
            ps.setTimestamp(7, Conversions.getCurrentTimestamp());
            ps.setString(8, DBUser.getCurrentUser());
            ps.setInt(9, customerId);
            ps.setInt(10, userId);
            ps.setInt(11, contactId);
            ps.setInt(12, id);
            ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * This method deletes an appointment
     * @param appointmentId the appointmentId that is being deleted
     */
    public static void deleteAppointment(int appointmentId) {

        try {
            String sql = "DELETE FROM appointments WHERE Appointment_ID = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setInt(1, appointmentId);
            ps.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * This method checks to see if a particular customer has an appointment
     * @param customerId the customerId to check in the database for appointments
     * @return returns the length the the list of appointments
     */
    public static int checkCustomerAppointments(int customerId) {

        ObservableList<Integer> appointments = FXCollections.observableArrayList();
        try {

            String sql = "SELECT Appointment_ID FROM appointments WHERE Customer_ID = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                appointments.add(appointmentId);

            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return appointments.size();
    }

    /**
     * This method creates an observable list of appointments by type
     * @param type the parameter that is being searched for in the database
     * @return returns a list of appointments of the given type
     */
    public static ObservableList<Appointment> getAppointmentsByType(String type) {

        ObservableList<Appointment> alist = FXCollections.observableArrayList();

        try {
            String sql = "SELECT Appointment_ID, Start, Customer_ID from appointments WHERE Type = ?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setString(1, type);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                Timestamp startDate = rs.getTimestamp("Start");
                int customerId = rs.getInt("Customer_ID");

                Appointment a = new Appointment(appointmentId, startDate, customerId);


                alist.add(a);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alist;
    }

    /**
     * This method creates an observable list of types to be used in a combobox
     * @return returns a list of the types
     */
    public static ObservableList<String> getAllTypes(){

        ObservableList<String> tlist = FXCollections.observableArrayList();

        try{
            String sql = "SELECT DISTINCT Type FROM appointments";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                String type = rs.getString("Type");
                tlist.add(type);
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return tlist;
    }

    /**
     * This method checks the next 15 minutes for an appointment
     */
    public static void appointmentCheck(){

        ObservableList<String> alist = FXCollections.observableArrayList();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
        Timestamp holder = new Timestamp(System.currentTimeMillis());
        String currentTime = formatter.format(holder);

        try {
            String sql = "SELECT Appointment_ID, Start FROM appointments WHERE Start BETWEEN CONVERT('"+currentTime+"', DATETIME) AND CONVERT('"+currentTime+"', DATETIME) + INTERVAL 15 MINUTE";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int appointmentId = rs.getInt("Appointment_ID");
                Timestamp startDate = rs.getTimestamp("Start");
                String id = String.valueOf(appointmentId);
                String start = String.valueOf(startDate);
                String appointment = "Appointment ID: " + id + " | Start Date and Time: " + start;

                alist.add(appointment);

                }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (alist.size() > 0) {
            Log.upcomingAppointmentAlert(alist);
        }
        else {
            Log.noUpcomingAppointment();
        }
    }

    /**
     * This method returns a list of appointments by the customer selected
     * @param customerId the customer that the appointments are for
     * @return returns a list of appointments by the specific customer
     */
    public static ObservableList<Appointment> getAppointmentsByCustomer(int customerId) {

        ObservableList<Appointment> alist = FXCollections.observableArrayList();

        try {
            String sql = "SELECT Appointment_ID, Title, Description, Type, Start, End, Contact_ID from appointments WHERE Customer_ID = ?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String type = rs.getString("Type");
                Timestamp startDate = rs.getTimestamp("Start");
                Timestamp endDate = rs.getTimestamp("End");
                int contactId = rs.getInt("Contact_ID");
                Appointment a = new Appointment(appointmentId, title, description, type, startDate, endDate, customerId, contactId);

                alist.add(a);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alist;
    }

    /**
     * This method returns a list of appointments by the contact selected
     * @param contactId the contact that the appointments are for
     * @return returns a list of appointments by the specific contact
     */
    public static ObservableList<Appointment> getAppointmentsByContact(int contactId) {

        ObservableList<Appointment> alist = FXCollections.observableArrayList();

        try {
            String sql = "SELECT Appointment_ID, Title, Description, Type, Start, End, Customer_ID from appointments WHERE Contact_ID = ?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, contactId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String type = rs.getString("Type");
                Timestamp startDate = rs.getTimestamp("Start");
                Timestamp endDate = rs.getTimestamp("End");
                int customerId = rs.getInt("Customer_ID");
                Appointment a = new Appointment(appointmentId, title, description, type, startDate, endDate, customerId, contactId);

                alist.add(a);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alist;

    }

    /**
     * This method returns a list of appointments by the month
     * @param month that the appointments are for
     * @return returns a list of appointments by the month
     */
    public static ObservableList<Appointment> getAppointmentsByMonth(int month){

        ObservableList<Appointment> alist = FXCollections.observableArrayList();

        try {
            String sql = "SELECT Appointment_ID, Start, Customer_ID from appointments WHERE MONTH(Start) = ?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setInt(1, month);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                Timestamp startDate = rs.getTimestamp("Start");
                int customerId = rs.getInt("Customer_ID");

                Appointment a = new Appointment(appointmentId, startDate, customerId);


                alist.add(a);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alist;
    }

    /**
     * This method returns a list of appointments by the month
     * @param month that the appointments are for
     * @return returns a list of appointments by the month
     */
    public static ObservableList<Appointment> getAppointmentsByTypeMonth(String type, int month){

        ObservableList<Appointment> alist = FXCollections.observableArrayList();

        try {
            String sql = "SELECT Appointment_ID, Start, Customer_ID from appointments WHERE Type = ? AND MONTH(Start) = ?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setString(1, type);
            ps.setInt(2, month);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                Timestamp startDate = rs.getTimestamp("Start");
                int customerId = rs.getInt("Customer_ID");

                Appointment a = new Appointment(appointmentId, startDate, customerId);


                alist.add(a);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alist;
    }

}
