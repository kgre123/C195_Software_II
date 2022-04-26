package helper;

import javafx.scene.control.Alert;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.ZoneId;
import java.sql.Timestamp;
import java.time.temporal.ChronoUnit;

public class Conversions {

    public static String getTimeZone(){

        ZoneId zone = ZoneId.systemDefault();
        return zone.toString();
    }

    public static Timestamp getCurrentTimestamp(){

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp holder = new Timestamp(System.currentTimeMillis());
        return Timestamp.valueOf(formatter.format(holder));
    }

    //public static boolean businessOpen(Timestamp start, Timestamp end){


    //}

    public static void appointmentIn15(Timestamp start){

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp holder = new Timestamp(System.currentTimeMillis());
        Timestamp.valueOf(formatter.format(holder));
        long difference = holder.getTime() - start.getTime();
        long diffMin = difference / (60 * 1000) % 60;

        if(diffMin < 15){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Appointment in Next 15 Minutes");
            alert.setContentText("Are you sure that you would like to delete the selected item?");
        }
    }
}
