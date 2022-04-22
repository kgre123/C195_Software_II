package controller;

import dbConnections.DBAppointment;
import dbConnections.DBContact;
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
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.ResourceBundle;

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

    public void onActionUpdate(ActionEvent actionEvent) throws IOException {

        try {
            String title = titleText.getText();
            String description = descriptionText.getText();
            String location = locationText.getText();
            String type = typeText.getText();

            String startDate = startDateText.getText();
            String startTime = startTimeText.getText();
            String fullStart = startDate + " " + startTime;
            Timestamp start = Timestamp.valueOf(fullStart);
            //System.out.println(start);

            String endDate = endDateText.getText();
            String endTime = endTimeText.getText();
            String fullEnd = endDate + " " + endTime;
            Timestamp end = Timestamp.valueOf(fullEnd);
            //System.out.println(end);

            int customerId = Integer.parseInt(customerIdText.getText());
            int userId = Integer.parseInt(userIdText.getText());
            int contactID =  contactComboBox.getValue().getContactId();

            DBAppointment.updateAppointment(title, description, location, type, start, end, customerId, userId, contactID);

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
