package controller;

import dbConnections.DBCountry;
import dbConnections.DBCustomer;
import dbConnections.DBDivision;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

public class AddCustomer implements Initializable {

    Parent scene;

    @FXML
    public Label addCustomerLabel;

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
    public TextField postalCodeText;

    @FXML
    public TextField phoneNumberText;

    @FXML
    public ComboBox<FirstLevelDivision> flDivisionComboBox;

    @FXML
    public ComboBox<Country> countryDataComboBox;

    @FXML
    public Button addButton;

    @FXML
    public Button cancelButton;


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
            customer.setCustomerAddress(addressText.getText());
            customer.setCustomerZip(postalCodeText.getText());
            customer.setCustomerPhone(phoneNumberText.getText());
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
}
