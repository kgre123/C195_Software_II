package controller;

import dbConnections.DBContact;
import dbConnections.DBCountry;
import dbConnections.DBDivision;
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
import model.Contact;
import model.Country;
import model.Customer;
import model.FirstLevelDivision;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
    public ComboBox<FirstLevelDivision> flDivisionComboBox;

    @FXML
    public ComboBox<Country> countryDataComboBox;

    @FXML
    public Button modifyButton;

    @FXML
    public Button cancelButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<FirstLevelDivision> dlist = DBDivision.getAllDivisionIds();
        flDivisionComboBox.setItems(dlist);
        flDivisionComboBox.setVisibleRowCount(10);

        ObservableList<Country> clist = DBCountry.getAllCountryIds();
        countryDataComboBox.setItems(clist);
        countryDataComboBox.setVisibleRowCount(3);

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

    public void getCustomerInfo(Customer customer) throws SQLException {

        customerIdText.setText(String.valueOf(customer.getCustomerId()));
        nameText.setText(customer.getCustomerName());
        addressText.setText(String.valueOf(customer.getCustomerAddress()));
        PostalCodeText.setText(String.valueOf(customer.getCustomerZip()));
        phoneNumberText.setText(String.valueOf(customer.getCustomerPhone()));
        FirstLevelDivision f = DBDivision.returnDivision(customer.getCustomerDivisionId());
        flDivisionComboBox.setValue(f);
        Country c = DBCountry.returnCountry(customer.getCustomerCountryId());
        countryDataComboBox.setValue(c);
    }
}
