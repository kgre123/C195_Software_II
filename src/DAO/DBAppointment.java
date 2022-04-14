package DAO;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class DBAppointment {

    public static ObservableList<Appointment> getAllAppointments(){

        ObservableList<Appointment> alist = FXCollections.observableArrayList();

        try{
            String sql = "SELECT * from appointments";

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


}
