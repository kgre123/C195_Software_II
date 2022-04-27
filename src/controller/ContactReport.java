package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ContactReport {
    public TableView contactTable;
    public TableColumn appointmentIdColumn;
    public TableColumn appointmentTitleColumn;
    public TableColumn appointmentTypeColumn;
    public TableColumn appointmentDescriptionColumn;
    public TableColumn appointmentStartColumn;
    public TableColumn appointmentEndColumn;
    public TableColumn customerIdColumn;
    public ComboBox contactComboBox;
    public Label selectContactLabel;
    public Button backButton;
    public Button refreshButton;

    public void onCombo(ActionEvent actionEvent) {
    }

    public void onActionBack(ActionEvent actionEvent) throws IOException {

        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/ReportView.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void onActionRefresh(ActionEvent actionEvent) {
    }
}
