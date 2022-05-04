package controller;

import dbConnections.DBAppointment;
import dbConnections.DBContact;
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
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.TimeZone;

/**
 * @author Kyle Green
 * This is the update appointment controller
 */
public class UpdateAppointment implements Initializable {

    Parent scene;

    /** update appointment label */
    @FXML public Label updateAppointmentLabel;

    /** appointment id label */
    @FXML public Label appointmentIdLabel;

    /** appointment title label */
    @FXML public Label titleLabel;

    /** appointment description label */
    @FXML public Label descriptionLabel;

    /** appointment location label */
    @FXML public Label locationLabel;

    /** appointment contact label */
    @FXML public Label contactLabel;

    /** appointment type label */
    @FXML public Label typeLabel;

    /** customer id label */
    @FXML public Label customerIdLabel;

    /** user id label */
    @FXML public Label userIdLabel;

    /** appointment id textbox */
    @FXML public TextField appointmentIdText;

    /** appointment title textbox*/
    @FXML public TextField titleText;

    /** appointment description textbox */
    @FXML public TextField descriptionText;

    /** appointment textbox */
    @FXML public TextField locationText;

    /** appointment type textbox */
    @FXML public TextField typeText;

    /** customer id textbox */
    @FXML public TextField customerIdText;

    /** user id textbox */
    @FXML public TextField userIdText;

    /** appointment contact combobox*/
    @FXML public ComboBox<Contact> contactComboBox;

    /** appointment update button */
    @FXML public Button updateButton;

    /** cancel button */
    @FXML public Button cancelButton;

    /** appointment start date label */
    @FXML public Label startDateLabel;

    /** appointment end date label */
    @FXML public Label endDateLabel;

    /** appointment start time label */
    @FXML public Label startTimeLabel;

    /** appointment end time label */
    @FXML public Label endTimeLabel;

    /** appointment start date textbox */
    @FXML public TextField startDateText;

    /** appointment end date textbox */
    @FXML public TextField endDateText;

    /** appointment start time textbox */
    @FXML public TextField startTimeText;

    /** appointment end time textbox */
    @FXML public TextField endTimeText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<Contact> clist = DBContact.getAllContacts();
        contactComboBox.setItems(clist);
        contactComboBox.setVisibleRowCount(3);

    }

    /**
     * This method updates selected appointment with the information from the user
     * @param actionEvent when the button is clicked
     * @throws IOException error
     */
    public void onActionUpdate(ActionEvent actionEvent) throws IOException {


        try{
            String title = titleText.getText();
            if(titleText.getText().isEmpty()){
                Appointment.emptyTitle();
                return;
            }

            String description = descriptionText.getText();
            if(descriptionText.getText().isEmpty()){
                Appointment.emptyDescription();
                return;
            }

            String location = locationText.getText();
            if(locationText.getText().isEmpty()){
                Appointment.emptyLocation();
                return;
            }

            String type = typeText.getText();
            if(typeText.getText().isEmpty()){
                Appointment.emptyType();
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

            int customerId = Integer.parseInt(customerIdText.getText());
            int id = Integer.parseInt(appointmentIdText.getText());
            ObservableList<Appointment> customerAppointments = DBAppointment.getAppointmentsByCustomer(Integer.parseInt(customerIdText.getText()));
            int userId = Integer.parseInt(userIdText.getText());
            int contactId = contactComboBox.getValue().getContactId();


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
                if((newStart.isAfter(startA) && newStart.isBefore(endA) && a.getAppointmentId() != id) || newStart.isEqual(startA)){
                    Customer.customerOverlappingAppointments();
                    return;
                } else if((newEnd.isAfter(startA) && newEnd.isBefore(endA) && a.getAppointmentId() != id) || newEnd.isEqual(endA)) {
                    Customer.customerOverlappingAppointments();
                    return;
                }
            }
            //making sure that the proposed appointment times are during business hours and that the start is before the end
            if(((startZDT.isAfter(openToLocalZDT)) || (startZDT.equals(openToLocalZDT))) && ((endZDT.isBefore(closeToLocalZDT)) || (endZDT.equals(closeToLocalZDT)))){
                if(start.before(end)) {
                    DBAppointment.updateAppointment(title, description, location, type, start, end, customerId, userId, contactId, id);
                }
                else{
                    Appointment.startBeforeEnd();
                    return;
                }

            } else {
                Conversions.invalidHours();
                return;
            }

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
     * This method takes the user to the appointment screen
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

    /**
     * This method retrieves information about the selected appointment and returns that info to the form
     * @param appointment the appointment information to remove from the database
     * @throws SQLException error
     */
    public void getAppointmentInfo(Appointment appointment) throws SQLException {

        appointmentIdText.setText(String.valueOf(appointment.getAppointmentId()));
        titleText.setText(appointment.getTitle());
        descriptionText.setText(String.valueOf(appointment.getDescription()));
        locationText.setText(String.valueOf(appointment.getLocation()));
        typeText.setText(String.valueOf(appointment.getType()));
        customerIdText.setText(String.valueOf(appointment.getCustomerId()));
        userIdText.setText(String.valueOf(appointment.getUserId()));
        Contact c = DBContact.returnContact(appointment.getContactId());
        contactComboBox.setValue(c);

        startDateText.setText(String.valueOf(appointment.getStartDay(appointment.getStartDate())));
        startTimeText.setText(String.valueOf(appointment.getStartTime(appointment.getStartDate())));
        endDateText.setText(String.valueOf(appointment.getEndDay(appointment.getEndDate())));
        endTimeText.setText(String.valueOf(appointment.getEndTime(appointment.getEndDate())));
    }
}
