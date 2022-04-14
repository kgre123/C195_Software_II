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

public class ModifyCustomer implements Initializable {

    Parent scene;

    @FXML
    public Label modifyCustomerLabel;

    @FXML
    public Label customerIdLabel;

    @FXML
    public Label nameLabel;

    @FXML
    public Label addressLabel;

    @FXML
    public Label postalCodeLabel;

    @FXML
    public Label phoneNumberLabel;

    @FXML
    public Label flDivisionLabel;

    @FXML
    public Label countryDataLabel;

    @FXML
    public TextField customerIdText;

    @FXML
    public TextField nameText;

    @FXML
    public TextField addressText;

    @FXML
    public TextField PostalCodeText;

    @FXML
    public TextField phoneNumberText;

    @FXML
    public ComboBox flDivisionComboBox;

    @FXML
    public ComboBox countryDataComboBox;

    @FXML
    public Button modifyButton;

    @FXML
    public Button cancelButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void onActionModify(ActionEvent actionEvent) {
    }

    /**
     * This method takes the user to the customer screen with customer information
     *
     * @param actionEvent when the button is clicked
     * @throws IOException error
     */
    public void onActionCancel(ActionEvent actionEvent) throws IOException {

        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/CustomerView.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();

    }
}
