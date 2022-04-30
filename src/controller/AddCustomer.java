package controller;

import dbConnections.DBCountry;
import dbConnections.DBCustomer;
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
import model.Country;
import model.Customer;
import model.FirstLevelDivision;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * @author Kyle Green
 * This is the add customer controller
 */
public class AddCustomer implements Initializable {

    Parent scene;

    /** add customer label */
    @FXML public Label addCustomerLabel;

    /** customer id label */
    @FXML public Label customerIdLabel;

    /** customer name label */
    @FXML public Label nameLabel;

    /** customer address label */
    @FXML public Label addressLabel;

    /** customer zip label */
    @FXML public Label postalCodeLabel;

    /** customer phone number label */
    @FXML public Label phoneNumberLabel;

    /** customer division label */
    @FXML public Label flDivisionLabel;

    /** customer country label */
    @FXML public Label countryDataLabel;

    /** customer id textbox */
    @FXML public TextField customerIdText;

    /** customer name textbox */
    @FXML public TextField nameText;

    /** customer address textbox */
    @FXML public TextField addressText;

    /** customer zip textbox */
    @FXML public TextField postalCodeText;

    /** customer phone textbox */
    @FXML public TextField phoneNumberText;

    /** customer division combobox */
    @FXML public ComboBox<FirstLevelDivision> flDivisionComboBox;

    /** customer country combobox */
    @FXML public ComboBox<Country> countryDataComboBox;

    /** add customer button */
    @FXML public Button addButton;

    /** cancel button*/
    @FXML public Button cancelButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<Country> clist = DBCountry.getAllCountryIds();
        countryDataComboBox.setItems(clist);
        countryDataComboBox.setVisibleRowCount(3);

        ObservableList<FirstLevelDivision> dlist = DBDivision.getAllDivisionIds();
        flDivisionComboBox.setItems(dlist);
        flDivisionComboBox.setVisibleRowCount(10);

    }
    /**
     * This method adds the customer to the database and retains their information
     *
     * @param actionEvent when the button is clicked
     * @throws NumberFormatException error
     */
    public void onActionAdd(ActionEvent actionEvent) {

        try{
            Customer customer = new Customer();

            customer.setCustomerName(nameText.getText());
            if(nameText.getText().isEmpty()){
                Customer.emptyName();
                return;
            }

            customer.setCustomerAddress(addressText.getText());
            if(addressText.getText().isEmpty()){
                Customer.emptyAddress();
                return;
            }
            customer.setCustomerZip(postalCodeText.getText());
            if(postalCodeText.getText().isEmpty()){
                Customer.emptyZip();
                return;
            }
            customer.setCustomerPhone(phoneNumberText.getText());
            if(phoneNumberText.getText().isEmpty()){
                Customer.emptyPhoneNumber();
                return;
            }
            customer.setCustomerDivisionId(flDivisionComboBox.getValue().getDivisionID());

            DBCustomer.addCustomer(customer);

            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/CustomerView.fxml")));
            stage.setScene(new Scene(scene));
            stage.show();
        }
        catch(NumberFormatException | IOException | SQLException e){
             e.printStackTrace();
        }
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

    /**
     * This method filters the division combobox based on the country selected
     * @param actionEvent event
     * @throws SQLException error
     */
    public void onCountryCombo(ActionEvent actionEvent) throws SQLException {

        int countryId = countryDataComboBox.getSelectionModel().getSelectedItem().getCountryId();

        if (countryId == 0){
            ObservableList<FirstLevelDivision> dlist = DBDivision.getAllDivisionIds();
            flDivisionComboBox.setItems(dlist);
            flDivisionComboBox.setVisibleRowCount(10);
        }
        else {
            ObservableList<FirstLevelDivision> dlist = DBDivision.returnDivisionByCountry(countryId);
            flDivisionComboBox.setItems(dlist);
            flDivisionComboBox.setVisibleRowCount(10);
        }
    }
}
