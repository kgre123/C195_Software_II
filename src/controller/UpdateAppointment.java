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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Appointment;
import model.Contact;
import model.Customer;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.TimeZone;

public class UpdateAppointment implements Initializable {

    Parent scene;

    @FXML
    public Label updateAppointmentLabel;

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
    public Button updateButton;

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
     * This method updates selected appointment with the information from the user
     * @param actionEvent when the button is clicked
     */
    public void onActionUpdate(ActionEvent actionEvent) throws IOException {

        try {

            int id = Integer.parseInt(appointmentIdText.getText());
            String title = titleText.getText();
            String description = descriptionText.getText();
            String location = locationText.getText();
            String type = typeText.getText();


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

            int customerId = Integer.parseInt(customerIdText.getText());
            int userId = Integer.parseInt(userIdText.getText());
            int contactId = contactComboBox.getValue().getContactId();

            if(startComparison >= 0 && endComparison <= 0) {
                Timestamp startTest = Timestamp.valueOf(fullStart);
                Timestamp endTest = Timestamp.valueOf(fullEnd);
                DBAppointment.updateAppointment(title, description, location, type, startTest, endTest, customerId, userId, contactId, id);
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
