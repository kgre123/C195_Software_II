package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminCustomerView implements Initializable {

    @FXML
    public TableView customerTable;

    @FXML
    public TableColumn customerIdColumn;

    @FXML
    public TableColumn nameColumn;

    @FXML
    public TableColumn addressColumn;

    @FXML
    public TableColumn postalCodeColumn;

    @FXML
    public TableColumn phoneNumberColumn;

    @FXML
    public TableColumn flDivisionColumn;

    @FXML
    public TableColumn countryDataColumn;

    @FXML
    public Button addCustomerButton;

    @FXML
    public Button modifyCustomerButton;

    @FXML
    public Button deleteCustomerButton;

    @FXML
    public Button exitButton;

    @FXML
    public Label customerLabel;

    @FXML
    public Button backButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void onActionAddCustomer(ActionEvent actionEvent) {
    }

    public void onActionModifyCustomer(ActionEvent actionEvent) {
    }

    public void onActionDeleteCustomer(ActionEvent actionEvent) {
    }

    /**
     * This method exits the program.
     * @param actionEvent when the button is clicked
     */
    public void onActionExitProgram(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void onActionGoBack(ActionEvent actionEvent) {
    }
}
