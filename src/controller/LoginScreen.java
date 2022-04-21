package controller;

import dbConnections.DBUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
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

    @FXML
    public Label regionCode;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void onActionLogin(ActionEvent actionEvent) throws IOException {

        if(UsernameText.getText().isEmpty()){
            User.blankUsername();
            return;
        }
        if(PasswordText.getText().isEmpty()){
            User.blankPassword();
        }

        String username = UsernameText.getText();
        String password = PasswordText.getText();

        boolean login = DBUser.userLogin(username, password);

        if(!login){
            User.incorrectUserNameOrPassword();
        }
        else{
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Object scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/MainScreen.fxml")));
            stage.setScene(new Scene ((Parent)scene));
            stage.show();
        }
    }

    /**
     * This method exits the program.
     * @param actionEvent when the button is clicked
     */
    public void onActionCancel(ActionEvent actionEvent) {

        System.exit(0);
    }
}
