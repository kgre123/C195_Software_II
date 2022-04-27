package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.Parent;
import java.io.IOException;
import java.util.Objects;

public class ReportView {

    @FXML
    public Label report1Label;

    @FXML
    public Label report2Label;

    @FXML
    public Label report3Label;

    @FXML
    public Button report1Button;

    @FXML
    public Button report2Button;

    @FXML
    public Button report3Button;

    @FXML
    public Button backButton;

    public void onActionGenReport1(ActionEvent actionEvent) throws IOException {

        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/TypeMonthReportView.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void onActionGenReport2(ActionEvent actionEvent) throws IOException {

        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/ContactReportView.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void onActionGenReport3(ActionEvent actionEvent) throws IOException {

        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/CustomerReportView.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void onActionBack(ActionEvent actionEvent) throws IOException {

        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Object scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/MainScreen.fxml")));
        stage.setScene(new Scene ((Parent)scene));
        stage.show();
    }
}
