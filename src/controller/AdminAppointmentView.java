package controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminAppointmentView implements Initializable {

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

    public void onActionAddAppointment(ActionEvent actionEvent) {
    }

    public void OnActionUpdateAppointment(ActionEvent actionEvent) {
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

    public void onActionShowWeekly(Event event) {
    }

    public void onActionGoBack(ActionEvent actionEvent) {
    }

    public void onActionShowMonthly(Event event) {
    }
}
