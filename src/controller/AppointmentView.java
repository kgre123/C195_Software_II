package controller;

import dbConnections.DBAppointment;
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
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * @author Kyle Green
 * This is the appointment view controller
 */
public class AppointmentView implements Initializable {

    Parent scene;

    /** add button */
    @FXML public Button addButton;

    /** update button */
    @FXML public Button updateButton;

    /** delete button */
    @FXML public Button deleteButton;

    /** exit button */
    @FXML public Button exitButton;

    /** appointments label */
    @FXML public Label appointmentsLabel;

    /** weekly appointments tab */
    @FXML public Tab weeklyAppointmentsTab;

    /** weekly appointments table */
    @FXML public TableView<Appointment> weeklyAppointmentTable;

    /** appointment id column */
    @FXML public TableColumn<Appointment, Integer> weeklyAppointmentIdColumn;

    /** appointment title column */
    @FXML public TableColumn<Appointment, String> weeklyTitleColumn;

    /** appointment description column */
    @FXML public TableColumn<Appointment, String> weeklyDescriptionColumn;

    /** appointment location column */
    @FXML public TableColumn<Appointment, String> weeklyLocationColumn;

    /** appointment contact column */
    @FXML public TableColumn<Appointment, Integer> weeklyContactColumn;

    /** appointment type column */
    @FXML public TableColumn<Appointment, String> weeklyTypeColumn;

    /** appointment start column */
    @FXML public TableColumn<Appointment, Timestamp> weeklyStartColumn;

    /** appointment end column */
    @FXML public TableColumn<Appointment, Timestamp> weeklyEndColumn;

    /** customer id column */
    @FXML public TableColumn<Appointment, Integer> weeklyCustomerIdColumn;

    /** user id column */
    @FXML public TableColumn<Appointment, Integer> weeklyUserIdColumn;

    /** monthly appointments tab */
    @FXML public Tab monthlyAppointmentsTab;

    /** monthly appointments table */
    @FXML public TableView<Appointment> monthlyAppointmentTable;

    /** appointment id column */
    @FXML public TableColumn<Appointment, Integer> monthlyAppointmentIdColumn;

    /** appointment title column */
    @FXML public TableColumn<Appointment, String> monthlyTitleColumn;

    /** appointment description column */
    @FXML public TableColumn<Appointment, String> monthlyDescriptionColumn;

    /** appointment location column */
    @FXML public TableColumn<Appointment, String> monthlyLocationColumn;

    /** appointment contact column */
    @FXML public TableColumn<Appointment, Integer> monthlyContactColumn;

    /** appointment type column */
    @FXML public TableColumn<Appointment, String> monthlyTypeColumn;

    /** appointment start column */
    @FXML public TableColumn<Appointment, Timestamp> monthlyStartColumn;

    /** appointment end column */
    @FXML public TableColumn<Appointment, Timestamp> monthlyEndColumn;

    /** customer id column */
    @FXML public TableColumn<Appointment, Integer> monthlyCustomerIdColumn;

    /** user id column */
    @FXML public TableColumn<Appointment, Integer> monthlyUserIdColumn;

    /** back button */
    @FXML public Button backButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        weeklyAppointmentTable.setItems(DBAppointment.getAllWeeklyAppointments());
        weeklyAppointmentIdColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        weeklyTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        weeklyDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        weeklyLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        weeklyContactColumn.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        weeklyTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        weeklyStartColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        weeklyEndColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        weeklyCustomerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        weeklyUserIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));

        monthlyAppointmentTable.setItems(DBAppointment.getAllMonthlyAppointments());
        monthlyAppointmentIdColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        monthlyTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        monthlyDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        monthlyLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        monthlyContactColumn.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        monthlyTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        monthlyStartColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        monthlyEndColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        monthlyCustomerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        monthlyUserIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
    }

    /**
     * This method takes the user to the form to add an appointment
     *
     * @param actionEvent when the button is clicked
     * @throws IOException error
     */
    public void onActionAddAppointment(ActionEvent actionEvent) throws IOException {

        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/AddAppointment.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * This method takes the user to the form to update an appointment with existing information from the selected appointment
     *
     * @param actionEvent when the button is clicked
     * @throws IOException error
     * @throws SQLException error
     */
    public void OnActionUpdateAppointment(ActionEvent actionEvent) throws IOException, SQLException {

        if(weeklyAppointmentTable.getSelectionModel().getSelectedItem() != null) {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/UpdateAppointment.fxml"));
            loader.load();

            UpdateAppointment UAController = loader.getController();
            UAController.getAppointmentInfo(weeklyAppointmentTable.getSelectionModel().getSelectedItem());

            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        }

        else if(monthlyAppointmentTable.getSelectionModel().getSelectedItem() != null) {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/UpdateAppointment.fxml"));
            loader.load();

            UpdateAppointment UAController = loader.getController();
            UAController.getAppointmentInfo(monthlyAppointmentTable.getSelectionModel().getSelectedItem());

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
     * This method deletes the selected appointment or throws an error if nothing is selected
     *
     * @param actionEvent when the button is clicked
     * @throws IOException error
     */
    public void onActionDeleteAppointment(ActionEvent actionEvent) throws IOException {

        if(weeklyAppointmentTable.getSelectionModel().getSelectedItem() != null) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Delete");
            alert.setContentText("Are you sure that you would like to delete the selected appointment?");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent()) {

                if (result.get() == ButtonType.OK) {

                    Alert deleted = new Alert(Alert.AlertType.INFORMATION);
                    deleted.setTitle("Appointment Deleted");
                    deleted.setContentText("Appointment ID: " + weeklyAppointmentTable.getSelectionModel().getSelectedItem().getAppointmentId() + " | Type: " + weeklyAppointmentTable.getSelectionModel().getSelectedItem().getType() + " has been deleted!");
                    deleted.showAndWait();

                    DBAppointment.deleteAppointment(weeklyAppointmentTable.getSelectionModel().getSelectedItem().getAppointmentId());
                }
                else {
                    weeklyAppointmentTable.getSelectionModel().clearSelection();
                }
            }
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/AppointmentView.fxml")));
            stage.setScene(new Scene(scene));
            stage.show();
        }

        else if(monthlyAppointmentTable.getSelectionModel().getSelectedItem() != null) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Delete");
            alert.setContentText("Are you sure that you would like to delete the selected appointment?");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent()) {

                if (result.get() == ButtonType.OK) {
                    Alert deleted = new Alert(Alert.AlertType.INFORMATION);
                    deleted.setTitle("Appointment Deleted");
                    deleted.setContentText("Appointment ID: " + monthlyAppointmentTable.getSelectionModel().getSelectedItem().getAppointmentId() + " | Type: " + monthlyAppointmentTable.getSelectionModel().getSelectedItem().getType() + " has been deleted!");
                    deleted.showAndWait();

                    DBAppointment.deleteAppointment(monthlyAppointmentTable.getSelectionModel().getSelectedItem().getAppointmentId());
                }
                else {
                    monthlyAppointmentTable.getSelectionModel().clearSelection();
                }
            }
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/AppointmentView.fxml")));
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
     * This method takes the user to the main screen to select a customer or appointment
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
