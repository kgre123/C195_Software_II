package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class TypeMonthReport {
    public Label typeLabel;
    public ComboBox monthCombo;
    public ComboBox typeCombo;
    public Label monthLabel;
    public TableView monthTable;
    public TableColumn monthAppointmentId;
    public TableColumn monthStart;
    public TableColumn monthCustomerId;
    public TableView typeTable;
    public TableColumn typeAppointmentId;
    public TableColumn typeStart;
    public TableColumn typeCustomerId;
    public Button backButton;

    public void onMonthCombo(ActionEvent actionEvent) {
    }

    public void onTypeCombo(ActionEvent actionEvent) {
    }

    public void onActionBack(ActionEvent actionEvent) throws IOException {

        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/ReportView.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }
}
