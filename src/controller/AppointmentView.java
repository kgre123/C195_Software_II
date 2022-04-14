package controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
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
    public TableView weeklyAppointmentTable;

    @FXML
    public TableColumn weeklyAppointmentIdColumn;

    @FXML
    public TableColumn weeklyTitleColumn;

    @FXML
    public TableColumn weeklyDescriptionColumn;

    @FXML
    public TableColumn weeklyLocationColumn;

    @FXML
    public TableColumn weeklyContactColumn;

    @FXML
    public TableColumn weeklyTypeColumn;

    @FXML
    public TableColumn weeklyStartColumn;

    @FXML
    public TableColumn weeklyEndColumn;

    @FXML
    public TableColumn weeklyCustomerIdColumn;

    @FXML
    public TableColumn weeklyUserIdColumn;

    @FXML
    public Tab monthlyAppointmentsTab;

    @FXML
    public TableView monthlyAppointmentTable;

    @FXML
    public TableColumn monthlyAppointmentIdColumn;

    @FXML
    public TableColumn monthlyTitleColumn;

    @FXML
    public TableColumn monthlyDescriptionColumn;

    @FXML
    public TableColumn monthlyLocationColumn;

    @FXML
    public TableColumn monthlyContactColumn;

    @FXML
    public TableColumn monthlyTypeColumn;

    @FXML
    public TableColumn monthlyStartColumn;

    @FXML
    public TableColumn monthlyEndColumn;

    @FXML
    public TableColumn monthlyCustomerIdColumn;

    @FXML
    public TableColumn monthlyUserIdColumn;

    @FXML
    public Button backButton;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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
    public void OnActionUpdateAppointment(ActionEvent actionEvent) throws IOException {

        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/UpdateAppointment.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void onActionDeleteAppointment(ActionEvent actionEvent) {


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
