package controller;

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

import java.io.IOException;
import java.net.URL;
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
    public ComboBox contactComboBox;

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

    }

    public void onActionUpdate(ActionEvent actionEvent) {
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
}