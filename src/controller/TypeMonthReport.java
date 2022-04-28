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

public class TypeMonthReport implements Initializable {

    @FXML
    public Label typeLabel;

    @FXML
    public ComboBox<String> typeCombo;

    @FXML
    public Label monthLabel;

    @FXML
    public TableView<Appointment> monthTable;

    @FXML
    public TableColumn<Appointment, Integer> monthAppointmentId;

    @FXML
    public TableColumn<Appointment, Timestamp> monthStart;

    @FXML
    public TableColumn<Appointment, Integer> monthCustomerId;

    @FXML
    public TableView<Appointment> typeTable;

    @FXML
    public TableColumn<Appointment, Integer> typeAppointmentId;

    @FXML
    public TableColumn<Appointment, Timestamp> typeStart;

    @FXML
    public TableColumn<Appointment, Integer> typeCustomerId;

    @FXML
    public Button backButton;

    @FXML
    public Button selectTypeButton;

    @FXML
    public Button selectMonthButton;

    @FXML
    public TextField monthText;

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
