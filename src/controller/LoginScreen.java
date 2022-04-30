package controller;

import dbConnections.DBAppointment;
import dbConnections.DBUser;
import helper.Log;
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
import java.time.ZoneId;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * @author Kyle Green
 * This is the login screen controller
 */
public class LoginScreen implements Initializable {

    /** user login label */
    @FXML public Label UserLoginLabel;

    /** user login button */
    @FXML public Button LoginButton;

    /** cancel button */
    @FXML public Button CancelButton;

    /** username label */
    @FXML public Label UsernameLabel;

    /** password label */
    @FXML public Label PasswordLabel;

    /** username textbox */
    @FXML public TextField UsernameText;

    /** password textbox */
    @FXML public TextField PasswordText;

    /** region code label */
    @FXML public Label regionCode;

    interface getTimeZoneLambda {
        String getTimeZone(ZoneId z);
    }
    /**
     * This method initializes the login screen
     * @param url the url
     * @param resourceBundle the resource bundle used
     * The lambda here is to get the timezone ID to a string. It helped cut down on the lines of code and everything
     * with it is contained in one class. It makes things easier to follow, because everything used for it is found in this
     * particular file.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        /* This is one of the lambdas that I am using.
        This particular lambda is helpful because it cuts down on the lines of code that I had to write.
        I changed to this lambda from a method call in another class and now everything is contained in this controller
         */
        getTimeZoneLambda myLambda = (ZoneId z) -> z.toString();
        regionCode.setText(myLambda.getTimeZone(ZoneId.systemDefault()));

        ResourceBundle rb = ResourceBundle.getBundle("properties/Lang", Locale.getDefault());

        if(Locale.getDefault().getLanguage().equals("fr")) {

            UserLoginLabel.setText(rb.getString("username"));
            UsernameLabel.setText(rb.getString("username"));
            LoginButton.setText(rb.getString("login"));
            CancelButton.setText(rb.getString("cancel"));
            PasswordLabel.setText(rb.getString("password"));
        }
    }

    /**
     * This method runs the login event
     * @param actionEvent when the button is clicked
     * @throws IOException error
     */
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
            Log.logAttempts(username, password);
            User.incorrectUserNameOrPassword();
        }
        else{
            Log.logAttempts(username, password);

            DBAppointment.appointmentCheck();
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
