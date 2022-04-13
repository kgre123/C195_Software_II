package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminAddAppointment implements Initializable {


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
    public ComboBox contactComboBox;

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

    }

    public void onActionAdd(ActionEvent actionEvent) {
    }

    public void onActionCancel(ActionEvent actionEvent) {
    }
}
