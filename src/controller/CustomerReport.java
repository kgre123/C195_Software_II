package controller;

import dbConnections.DBAppointment;
import dbConnections.DBCountry;
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
import model.Country;
import model.Customer;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.ResourceBundle;

public class CustomerReport implements Initializable {

    @FXML
    public TableView<Appointment> customerTable;

    @FXML
    public TableColumn<Appointment, Integer> appointmentIdColumn;

    @FXML
    public TableColumn<Appointment, String> appointmentTitleColumn;

    @FXML
    public TableColumn<Appointment, String> appointmentTypeColumn;

    @FXML
    public TableColumn<Appointment, String> appointmentDescriptionColumn;

    @FXML
    public TableColumn<Appointment, Timestamp> appointmentStartColumn;

    @FXML
    public TableColumn<Appointment, Timestamp> appointmentEndColumn;

    @FXML
    public TableColumn<Appointment, Integer> contactIdColumn;

    @FXML
    public ComboBox<Customer> customerComboBox;

    @FXML
    public Label selectCustomerLabel;

    @FXML
    public Button backButton;

    @FXML
    public Button refreshButton;

    public void onCombo(ActionEvent actionEvent) {
    }

    /**
     * This method returns to the reports screen
     * @param actionEvent when the button is clicked
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
