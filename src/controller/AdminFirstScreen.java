package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminFirstScreen implements Initializable {

    @FXML
    public Label welcomeLabel;

    @FXML
    public Button customerButton;

    @FXML
    public Button appointmentButton;

    @FXML
    public Label instructionLabel;

    @FXML
    public Button exitButton;


    public void onActionCustomerScreen(ActionEvent actionEvent) {
    }

    public void onActionAppointmentScreen(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /**
     * This method exits the program.
     * @param actionEvent when the button is clicked
     */
    public void onActionExitProgram(ActionEvent actionEvent) {
        System.exit(0);
    }
}
