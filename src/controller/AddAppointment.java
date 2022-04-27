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
            appointment.setDescription(descriptionText.getText());
            appointment.setLocation(locationText.getText());
            appointment.setType(typeText.getText());

            //getting the dates ready to check times
            String startDate = startDateText.getText();
            String startTime = startTimeText.getText();
            String fullStart = startDate + " " + startTime;
            Timestamp start = Timestamp.valueOf(fullStart);
            String endDate = endDateText.getText();
            String endTime = endTimeText.getText();
            String fullEnd = endDate + " " + endTime;
            Timestamp end = Timestamp.valueOf(fullEnd);

            LocalDate businessOpenDate = LocalDate.of(2022, 04, 27);
            LocalTime businessOpenTime = LocalTime.of(8,00);
            ZoneId easternZoneId = ZoneId.of("America/Indiana/Vevay");
            ZonedDateTime openZDT = ZonedDateTime.of(businessOpenDate, businessOpenTime, easternZoneId);

            LocalDate businessCloseDate = LocalDate.of(2022, 04, 27);
            LocalTime businessCloseTime = LocalTime.of(22,00);
            ZonedDateTime closeZDT = ZonedDateTime.of(businessCloseDate, businessCloseTime, easternZoneId);

            ZoneId localZoneId = ZoneId.of(TimeZone.getDefault().getID());
            ZonedDateTime openToLocalZDT = openZDT.withZoneSameInstant(localZoneId);
            Timestamp openTimestamp = Timestamp.from(openToLocalZDT.toInstant());
            ZonedDateTime closeToLocalZDT = closeZDT.withZoneSameInstant(localZoneId);
            Timestamp closeTimestamp = Timestamp.from(closeToLocalZDT.toInstant());

            LocalTime startHours = start.toLocalDateTime().toLocalTime();
            LocalTime openHours = openTimestamp.toLocalDateTime().toLocalTime();
            LocalTime endHours = end.toLocalDateTime().toLocalTime();
            LocalTime closeHours = closeTimestamp.toLocalDateTime().toLocalTime();

            int startComparison = startHours.compareTo(openHours);
            int endComparison = endHours.compareTo(closeHours);
            if(startComparison >= 0 && endComparison <= 0) {
                appointment.setStartDate(start);
                appointment.setEndDate(end);
            } else {
                Conversions.invalidHours();
                return;
            }

            appointment.setCustomerId(Integer.parseInt(customerIdText.getText()));
            appointment.setUserId(Integer.parseInt(userIdText.getText()));
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
}
