package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginScreen implements Initializable {

    @FXML
    public Label UserLoginLabel;

    @FXML
    public Button LoginButton;

    @FXML
    public Button CancelButton;

    @FXML
    public Label UsernameLabel;

    @FXML
    public Label PasswordLabel;

    @FXML
    public TextField UsernameText;

    @FXML
    public TextField PasswordText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void onActionLogin(ActionEvent actionEvent) {
    }

    /**
     * This method exits the program.
     * @param actionEvent when the button is clicked
     */
    public void onActionCancel(ActionEvent actionEvent) {

        System.exit(0);
    }
}
