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

public class TypeMonthComboReport implements Initializable {

    @FXML
    public Label typeLabel;

    @FXML
    public ComboBox<String> typeCombo;

    @FXML
    public Label monthLabel;

    @FXML
    public TextField monthText;

    @FXML
    public TableView<Appointment> typeMonthTable;

    @FXML
    public TableColumn<Appointment, Integer> typeAppointmentId;

    @FXML
    public TableColumn<Appointment, Timestamp> typeStart;

    @FXML
    public TableColumn<Appointment, Integer> typeCustomerId;

    @FXML
    public Button backButton;

    @FXML
    public Button searchButton;

    public void onTypeCombo(ActionEvent actionEvent) {
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
    public void onSearch(ActionEvent actionEvent) {

        int monthNumber = Integer.parseInt(monthText.getText());
        if(monthText.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Month Entered");
            alert.setContentText("Please enter a month!");
            alert.showAndWait();
            return;
        }

        String type = typeCombo.getSelectionModel().getSelectedItem();
        if(type == null){
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("No Type Selected");
            alert1.setContentText("Please select a type!");
            alert1.showAndWait();
            return;
        }
        typeMonthTable.setItems(DBAppointment.getAppointmentsByTypeMonth(type, monthNumber));
        typeAppointmentId.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        typeStart.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        typeCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<String> tlist = DBAppointment.getAllTypes();
        typeCombo.setItems(tlist);
    }
}
