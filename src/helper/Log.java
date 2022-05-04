package helper;

import dbConnections.DBUser;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.Appointment;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author Kyle Green
 * This is the class that creates the log file for login attempts
 */
public class Log {

    /**
     * This method writes the login information to the log file
     * @param username the username that is being checked and logged
     * @param password the password that is being checked and logged
     */
    public static void logAttempts(String username, String password){

        BufferedWriter bw = null;

        try{
            bw = new BufferedWriter(new FileWriter("login_activity.txt", true));

            boolean login = DBUser.userLogin(username, password);

            if(login){
                String success = "User: " + username + "|| Successfully logged in at: " + LocalDateTime.now() + "\n";
                bw.write(success);
            }

            else {
                String failure = "User: " + username + "|| Failed to log in at: " + LocalDateTime.now() + "\n";
                bw.write(failure);
            }
        }

        catch (IOException e) {
            e.printStackTrace();
        }

        finally {

            try{
                if(bw!=null) {
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * This method creates an alert that displays when there are appointments coming up in the next 15 minutes
     * @param appointment the observable list of strings to use in the alert message
     */
    public static void upcomingAppointmentAlert(ObservableList<String> appointment) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Appointment Soon!");
        alert.setContentText(appointment + " starts within the next 15 minutes!");
        alert.showAndWait();
    }

    /**
     * This creates the alert that displays when there are no upcoming appointments
     */
    public static void noUpcomingAppointment(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("No Appointments Soon!");
        alert.setContentText("There are no appointments scheduled within the next 15 minutes.");
        alert.showAndWait();
    }
}
