package dbConnections;

import helper.Conversions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class DBAppointment {

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
}
