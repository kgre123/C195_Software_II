package controller;

import dbConnections.DBAppointment;
import dbConnections.DBCustomer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Customer;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class CustomerView implements Initializable {

    Parent scene;

    @FXML
    public TableView<Customer> customerTable;

    @FXML
    public TableColumn<Customer, Integer> customerIdColumn;

    @FXML
    public TableColumn<Customer, String> nameColumn;

    @FXML
    public TableColumn<Customer, String> addressColumn;

    @FXML
    public TableColumn<Customer, String> postalCodeColumn;

    @FXML
    public TableColumn<Customer, String> phoneNumberColumn;

    @FXML
    public TableColumn<Customer, Integer> flDivisionColumn;

    @FXML
    public TableColumn<Customer, Integer> countryDataColumn;

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

    /**
     * This initializes the customer view by filling the tables with the customer information
     *
     * @param url, resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        customerTable.setItems(DBCustomer.getAllCustomers());
        customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        postalCodeColumn.setCellValueFactory(new PropertyValueFactory<>("customerZip"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("customerPhone"));
        flDivisionColumn.setCellValueFactory(new PropertyValueFactory<>("customerDivisionId"));
        countryDataColumn.setCellValueFactory(new PropertyValueFactory<>("customerCountryId"));

    }

    /**
     * This method takes the user to the form to add a customer
     *
     * @param actionEvent when the button is clicked
     * @throws IOException error
     */
    public void onActionAddCustomer(ActionEvent actionEvent) throws IOException {

        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/AddCustomer.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * This method takes the user to the form to modify a customer with information from the selected customer
     *
     * @param actionEvent when the button is clicked
     * @throws IOException error
     */
    public void onActionModifyCustomer(ActionEvent actionEvent) throws IOException, SQLException {

        if(customerTable.getSelectionModel().getSelectedItem() != null) {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/ModifyCustomer.fxml"));
            loader.load();

            ModifyCustomer MCController = loader.getController();
            MCController.getCustomerInfo(customerTable.getSelectionModel().getSelectedItem());

            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        }
        else {

            Customer.itemSelectError();
        }

    }

    /**
     * This method takes the selected customer and goes through the deletion process. It will not delete the customer if the customer has appointments.
     *
     * @param actionEvent when the button is clicked
     * @throws IOException error
     */
    public void onActionDeleteCustomer(ActionEvent actionEvent) throws IOException {

        if(customerTable.getSelectionModel().getSelectedItem() != null) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Delete");
            alert.setContentText("Are you sure that you would like to delete the selected item?");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent()) {

                if (result.get() == ButtonType.OK) {
                    if (DBAppointment.checkCustomerAppointments(customerTable.getSelectionModel().getSelectedItem().getCustomerId()) > 0){
                        Customer.customerAppointmentError();
                    } else {
                        Alert deleted = new Alert(Alert.AlertType.INFORMATION);
                        deleted.setTitle("Customer Deleted");
                        deleted.setContentText("Customer: " + customerTable.getSelectionModel().getSelectedItem().getCustomerName() + " has been deleted!");

                        deleted.showAndWait();
                        DBCustomer.deleteCustomer(customerTable.getSelectionModel().getSelectedItem().getCustomerId());

                    }
                }
                else {
                    customerTable.getSelectionModel().clearSelection();
                }
            }
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/CustomerView.fxml")));
            stage.setScene(new Scene(scene));
            stage.show();
        }
        else {
            Customer.itemSelectError();
        }

    }

    /**
     * This method exits the program.
     * @param actionEvent when the button is clicked
     */
    public void onActionExitProgram(ActionEvent actionEvent) {
        System.exit(0);
    }

    /**
     * This method takes the user to the screen to select a customer or appointment
     *
     * @param actionEvent when the button is clicked
     * @throws IOException error
     */
    public void onActionGoBack(ActionEvent actionEvent) throws IOException {

        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/MainScreen.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }
}
