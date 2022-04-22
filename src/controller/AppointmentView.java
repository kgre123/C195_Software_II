package controller;

import dbConnections.DBAppointment;
import dbConnections.DBCustomer;
import javafx.event.ActionEvent;
import javafx.event.Event;
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

public class AppointmentView implements Initializable {

    Parent scene;

    @FXML
    public Button addButton;

    @FXML
    public Button updateButton;

    @FXML
    public Button deleteButton;

    @FXML
    public Button exitButton;

    @FXML
    public Label appointmentsLabel;

    @FXML
    public Tab weeklyAppointmentsTab;

    @FXML
    public TableView<Appointment> weeklyAppointmentTable;

    @FXML
    public TableColumn<Appointment, Integer> weeklyAppointmentIdColumn;

    @FXML
    public TableColumn<Appointment, String> weeklyTitleColumn;

    @FXML
    public TableColumn<Appointment, String> weeklyDescriptionColumn;

    @FXML
    public TableColumn<Appointment, String> weeklyLocationColumn;

    @FXML
    public TableColumn<Appointment, Integer> weeklyContactColumn;

    @FXML
    public TableColumn<Appointment, String> weeklyTypeColumn;

    @FXML
    public TableColumn<Appointment, Timestamp> weeklyStartColumn;

    @FXML
    public TableColumn<Appointment, Timestamp> weeklyEndColumn;

    @FXML
    public TableColumn<Appointment, Integer> weeklyCustomerIdColumn;

    @FXML
    public TableColumn<Appointment, Integer> weeklyUserIdColumn;

    @FXML
    public Tab monthlyAppointmentsTab;

    @FXML
    public TableView<Appointment> monthlyAppointmentTable;

    @FXML
    public TableColumn<Appointment, Integer> monthlyAppointmentIdColumn;

    @FXML
    public TableColumn<Appointment, String> monthlyTitleColumn;

    @FXML
    public TableColumn<Appointment, String> monthlyDescriptionColumn;

    @FXML
    public TableColumn<Appointment, String> monthlyLocationColumn;

    @FXML
    public TableColumn<Appointment, Integer> monthlyContactColumn;

    @FXML
    public TableColumn<Appointment, String> monthlyTypeColumn;

    @FXML
    public TableColumn<Appointment, Timestamp> monthlyStartColumn;

    @FXML
    public TableColumn<Appointment, Timestamp> monthlyEndColumn;

    @FXML
    public TableColumn<Appointment, Integer> monthlyCustomerIdColumn;

    @FXML
    public TableColumn<Appointment, Integer> monthlyUserIdColumn;

    @FXML
    public Button backButton;



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

    public void onActionDeleteAppointment(ActionEvent actionEvent) throws IOException {

        if(weeklyAppointmentTable.getSelectionModel().getSelectedItem() != null) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Delete");
            alert.setContentText("Are you sure that you would like to delete the selected item?");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent()) {

                if (result.get() == ButtonType.OK) {
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
            alert.setContentText("Are you sure that you would like to delete the selected item?");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent()) {

                if (result.get() == ButtonType.OK) {
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

    public void onActionShowWeekly(Event event) {
    }

    public void onActionShowMonthly(Event event) {
    }
}
