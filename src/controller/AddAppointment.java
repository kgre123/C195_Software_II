package controller;

import dbConnections.DBAppointment;
import dbConnections.DBContact;
import dbConnections.DBCustomer;
import helper.Conversions;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Appointment;
import model.Contact;
import model.Customer;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.TimeZone;

public class AddAppointment implements Initializable {

    Parent scene;

    @FXML
    public Label addAppointmentLabel;

    @FXML
    public Label appointmentIdLabel;

    @FXML
    public Label titleLabel;

    @FXML
    public Label descriptionLabel;

    @FXML
    public Label locationLabel;

    @FXML
    public Label contactLabel;

    @FXML
    public Label typeLabel;

    @FXML
    public Label customerIdLabel;

    @FXML
    public Label userIdLabel;

    @FXML
    public TextField appointmentIdText;

    @FXML
    public TextField titleText;

    @FXML
    public TextField descriptionText;

    @FXML
    public TextField locationText;

    @FXML
    public TextField typeText;

    @FXML
    public TextField customerIdText;

    @FXML
    public TextField userIdText;

    @FXML
    public ComboBox<Contact> contactComboBox;

    @FXML
    public Button addButton;

    @FXML
    public Button cancelButton;

    @FXML
    public Label startDateLabel;

    @FXML
    public Label endDateLabel;

    @FXML
    public Label startTimeLabel;

    @FXML
    public Label endTimeLabel;

    @FXML
    public TextField startDateText;

    @FXML
    public TextField endDateText;

    @FXML
    public TextField startTimeText;

    @FXML
    public TextField endTimeText;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<Contact> clist = DBContact.getAllContacts();
        contactComboBox.setItems(clist);
        contactComboBox.setVisibleRowCount(3);

    }

    /**
     * This method saves the information from the user as a new appointment in the database
     * @param actionEvent when the button is clicked
     */
    public void onActionAdd(ActionEvent actionEvent) {


        try{
            Appointment appointment = new Appointment();
            appointment.setTitle(titleText.getText());
            if(titleText.getText().isEmpty()){
                Appointment.emptyTitle();
                return;
            }

            appointment.setDescription(descriptionText.getText());
            if(descriptionText.getText().isEmpty()){
                Appointment.emptyDescription();
                return;
            }

            appointment.setLocation(locationText.getText());
            if(locationText.getText().isEmpty()){
                Appointment.emptyLocation();
                return;
            }
            appointment.setType(typeText.getText());
            if(typeText.getText().isEmpty()){
                Appointment.emptyType();
                return;
            }

            try{
                appointment.setCustomerId(Integer.parseInt(customerIdText.getText()));
            }
            catch(NumberFormatException e){
                Appointment.invalidCustomerId();
                return;
            }


            String startDate = startDateText.getText();
            if(startDateText.getText().isEmpty()){
                Appointment.emptyStartDate();
                return;
            }

            String startTime = startTimeText.getText();
            if(startTimeText.getText().isEmpty()){
                Appointment.emptyStartTime();
                return;
            }


            String endDate = endDateText.getText();
            if(endDateText.getText().isEmpty()){
                Appointment.emptyEndDate();
                return;
            }

            String endTime = endTimeText.getText();
            if(endTimeText.getText().isEmpty()){
                Appointment.emptyEndTime();
                return;
            }

            //getting the dates ready to move to database
            ObservableList<Appointment> list = DBAppointment.getAppointmentsByCustomer(Integer.parseInt(customerIdText.getText()));
            String fullStart = startDate + " " + startTime;
            Timestamp start = Timestamp.valueOf(fullStart);
            String fullEnd = endDate + " " + endTime;
            Timestamp end = Timestamp.valueOf(fullEnd);

            //getting the times ready to compare
            LocalDate userStartDate = LocalDate.parse(startDate);
            LocalTime userStartTime = LocalTime.parse(startTime);
            LocalDate userEndDate = LocalDate.parse(endDate);
            LocalTime userEndTime = LocalTime.parse(endTime);
            ZoneId localZoneId = ZoneId.of(TimeZone.getDefault().getID());
            ZonedDateTime startZDT = ZonedDateTime.of(userStartDate, userStartTime, localZoneId);
            ZonedDateTime endZDT = ZonedDateTime.of(userEndDate, userEndTime, localZoneId);
            //System.out.println(startZDT);
            //System.out.println(endZDT);

            //getting the zonedatetime for open and close
            ZoneId easternZoneId = ZoneId.of("America/Indiana/Vevay");
            LocalDate businessOpenDate = LocalDate.parse(startDate);
            LocalTime businessOpenTime = LocalTime.of(8,00);
            ZonedDateTime openZDT = ZonedDateTime.of(businessOpenDate, businessOpenTime, easternZoneId);
            LocalDate businessCloseDate = LocalDate.parse(startDate);
            LocalTime businessCloseTime = LocalTime.of(22,00);
            ZonedDateTime closeZDT = ZonedDateTime.of(businessCloseDate, businessCloseTime, easternZoneId);

            //changing open and close times to current timezone of system
            ZonedDateTime openToLocalZDT = openZDT.withZoneSameInstant(localZoneId);
            //System.out.println(openToLocalZDT);
            ZonedDateTime closeToLocalZDT = closeZDT.withZoneSameInstant(localZoneId);
            //System.out.println(closeToLocalZDT);

            //getting the local date times to compare for the start and end of appointment
            LocalDateTime newStart = start.toLocalDateTime();
            LocalDateTime newEnd = end.toLocalDateTime();

            //checking for overlapping appointments for a specific customer
            for(Appointment a: list){
                LocalDateTime startA = a.getStartDate().toLocalDateTime();
                //System.out.println(startA);
                LocalDateTime endA = a.getEndDate().toLocalDateTime();
                //System.out.println(endA);
                if(newStart.isAfter(startA) && newStart.isBefore(endA)){
                    Customer.customerOverlappingAppointments();
                    return;
                } else if(newEnd.isAfter(startA) && newEnd.isBefore(endA)){
                    Customer.customerOverlappingAppointments();
                    return;
                }
            }
            //making sure that the proposed appointment times are during business hours and that the start is before the end
            if(((startZDT.isAfter(openToLocalZDT)) || (startZDT.equals(openToLocalZDT))) && ((endZDT.isBefore(closeToLocalZDT)) || (endZDT.equals(closeToLocalZDT)))){
                if(start.before(end)) {
                    appointment.setStartDate(start);
                    appointment.setEndDate(end);
                }
                else{
                    Appointment.startBeforeEnd();
                    return;
                }

            } else {
                Conversions.invalidHours();
                return;
            }

            try{
                appointment.setUserId(Integer.parseInt(userIdText.getText()));
            }
            catch(NumberFormatException e){
                Appointment.invalidUserId();
                return;
            }


            appointment.setContactId(contactComboBox.getValue().getContactId());
            DBAppointment.addAppointment(appointment);

            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/AppointmentView.fxml")));
            stage.setScene(new Scene(scene));
            stage.show();
        }
        catch(NumberFormatException | IOException e){
            e.printStackTrace();
        }
    }

    /**
     * This method takes the user to the appointment screen with appointment information
     *
     * @param actionEvent when the button is clicked
     * @throws IOException error
     */
    public void onActionCancel(ActionEvent actionEvent) throws IOException {

        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/AppointmentView.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public static boolean checkOverlappingAppointments(){
        return true;
    }
}
