package controller;

import dbConnections.DBAppointment;
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
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * @author Kyle Green
 * This is the separate type and month controller
 */
public class TypeMonthReport implements Initializable {

    /** type label */
    @FXML public Label typeLabel;

    /** type combobox*/
    @FXML public ComboBox<String> typeCombo;

    /** month label */
    @FXML public Label monthLabel;

    /** month table */
    @FXML public TableView<Appointment> monthTable;

    /** month appointment id column */
    @FXML public TableColumn<Appointment, Integer> monthAppointmentId;

    /** month appointment start column */
    @FXML public TableColumn<Appointment, Timestamp> monthStart;

    /** month customer id column */
    @FXML public TableColumn<Appointment, Integer> monthCustomerId;

    /** type table */
    @FXML public TableView<Appointment> typeTable;

    /** type appointment id column */
    @FXML public TableColumn<Appointment, Integer> typeAppointmentId;

    /** type start column */
    @FXML public TableColumn<Appointment, Timestamp> typeStart;

    /** type customer id column */
    @FXML public TableColumn<Appointment, Integer> typeCustomerId;

    /** back button */
    @FXML public Button backButton;

    /** select type button */
    @FXML public Button selectTypeButton;

    /** select month button */
    @FXML public Button selectMonthButton;

    /** month textbox */
    @FXML public TextField monthText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<String> tlist = DBAppointment.getAllTypes();
        typeCombo.setItems(tlist);

    }

    /**
     * This method sets the month table based on user input
     * @param actionEvent event
     */
    public void onTypeCombo(ActionEvent actionEvent) {
         onTypeSelect(null);

    }

    /**
     * This method returns to the previous screen
     * @param actionEvent event
     * @throws IOException error
     */
    public void onActionBack(ActionEvent actionEvent) throws IOException {

        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/ReportView.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * This method sets the type table based on user input
     * @param actionEvent event
     */
    public void onTypeSelect(ActionEvent actionEvent) {

        String type = typeCombo.getSelectionModel().getSelectedItem();

        if(type != null){
            typeTable.setItems(DBAppointment.getAppointmentsByType(type));
            typeAppointmentId.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
            typeStart.setCellValueFactory(new PropertyValueFactory<>("startDate"));
            typeCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        }
    }

    /**
     * This method sets the month table based on user input
     * @param actionEvent event
     */
    public void onMonthSelect(ActionEvent actionEvent) {

        if (monthText.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Month Entered");
            alert.setContentText("Please enter a month!");
            alert.showAndWait();
        }
        else {
            int monthNumber = Integer.parseInt(monthText.getText());
            monthTable.setItems(DBAppointment.getAppointmentsByMonth(monthNumber));
            monthAppointmentId.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
            monthStart.setCellValueFactory(new PropertyValueFactory<>("startDate"));
            monthCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        }
    }
}
