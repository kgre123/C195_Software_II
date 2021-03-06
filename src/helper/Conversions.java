package helper;

import javafx.scene.control.Alert;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;

/**
 * @author Kyle Green
 * This class converts some times to be used in other areas
 */
public class Conversions {

    /**
     * This method returns the current time
     * @return returns the current time as a timestamp
     */
    public static Timestamp getCurrentTimestamp(){

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp holder = new Timestamp(System.currentTimeMillis());
        return Timestamp.valueOf(formatter.format(holder));
    }

    /**
     * This method creates an alert for when appointments are not scheduled in business hours
     */
    public static void invalidHours(){

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid Appointment Hours");
        alert.setContentText("The appointment is not scheduled during business hours. Please schedule between 8am - 10pm EST.");
        alert.showAndWait();
    }

}
