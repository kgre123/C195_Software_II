package dbConnections;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class DBAppointment {

    public static ObservableList<Appointment> getAllWeeklyAppointments(){

        ObservableList<Appointment> alist = FXCollections.observableArrayList();

        try{
            String sql = "SELECT * from appointments WHERE start BETWEEN NOW() AND DATE_ADD(NOW(), INTERVAL 7 DAY)";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
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
                Appointment a = new Appointment(appointmentId,title,description,location,type,startDate,endDate,customerId,userId,contactId);

                alist.add(a);
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return alist;
    }

    public static ObservableList<Appointment> getAllMonthlyAppointments(){

        ObservableList<Appointment> alist = FXCollections.observableArrayList();

        try{
            String sql = "SELECT * from appointments WHERE start BETWEEN NOW() AND DATE_ADD(NOW(), INTERVAL 1 MONTH)";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
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
                Appointment a = new Appointment(appointmentId,title,description,location,type,startDate,endDate,customerId,userId,contactId);

                alist.add(a);
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return alist;
    }

    public static void addAppointment(String title, String description, String location, String type, Timestamp startDate, Timestamp endDate, int customerId, int userId, int contactId) {

        try {
            String sql = "INSERT into appointments (Appointment_ID, Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID) VALUES(NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setString(1, title);
            ps.setString(2, description);
            ps.setString(3, location);
            ps.setString(4, type);
            ps.setTimestamp(5, startDate);
            ps.setTimestamp(6, endDate);
            ps.setInt(7, customerId);
            ps.setInt(8, userId);
            ps.setInt(9, contactId);
            ps.execute();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void updateAppointment(){

    }

    public static void deleteAppointment(int appointmentId){

        try{
            String sql = "DELETE FROM appointments WHERE Appointment_ID = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setInt(1, appointmentId);
            ps.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
