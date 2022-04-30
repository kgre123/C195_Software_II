package controller;

import dbConnections.DBAppointment;
import dbConnections.DBCustomer;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointment;
import model.Customer;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * @author Kyle Green
 * This is the customer report controller
 */
public class CustomerReport implements Initializable {

    /** customer table */
    @FXML public TableView<Appointment> customerTable;

    /** appointment id column */
    @FXML public TableColumn<Appointment, Integer> appointmentIdColumn;

    /** appointment title column */
    @FXML public TableColumn<Appointment, String> appointmentTitleColumn;

    /** appointment type column */
    @FXML public TableColumn<Appointment, String> appointmentTypeColumn;

    /** appointment description column */
    @FXML public TableColumn<Appointment, String> appointmentDescriptionColumn;

    /** appointment start column */
    @FXML public TableColumn<Appointment, Timestamp> appointmentStartColumn;

    /** appointment end column */
    @FXML public TableColumn<Appointment, Timestamp> appointmentEndColumn;

    /** contact id column */
    @FXML public TableColumn<Appointment, Integer> contactIdColumn;

    /** customer combobox */
    @FXML public ComboBox<Customer> customerComboBox;

    /** select customer label */
    @FXML public Label selectCustomerLabel;

    /** back button */
    @FXML public Button backButton;

    /** refresh button */
    @FXML public Button refreshButton;

    /**
     * This method refreshes the page to update the table when the combobox is changed
     * @param actionEvent when the button is clicked
     */
    public void onCombo(ActionEvent actionEvent) {
        onActionRefresh(null);
    }

    /**
     * This method returns to the reports screen
     * @param actionEvent when the button is clicked
     * @throws IOException error
     */
    public void onActionBack(ActionEvent actionEvent) throws IOException {

        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/ReportView.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * This method refreshes the page to update the table
     * @param actionEvent when the button is clicked
     */
    public void onActionRefresh(ActionEvent actionEvent) {

        int customerId = customerComboBox.getSelectionModel().getSelectedItem().getCustomerId();

        if(customerId > 0){
            customerTable.setItems(DBAppointment.getAppointmentsByCustomer(customerId));
            appointmentIdColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
            appointmentTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
            appointmentDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
            appointmentTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
            appointmentStartColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
            appointmentEndColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
            contactIdColumn.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<Customer> clist = DBCustomer.getCustomersById();
        customerComboBox.setItems(clist);
        customerComboBox.setVisibleRowCount(3);

        appointmentIdColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        appointmentTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        appointmentDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        appointmentTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        appointmentStartColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        appointmentEndColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        contactIdColumn.setCellValueFactory(new PropertyValueFactory<>("contactId"));

    }
}
