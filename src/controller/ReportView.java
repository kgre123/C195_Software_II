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

/**
 * @author Kyle Green
 * This is the report view controller
 */
public class ReportView {

    /** type and month report label */
    @FXML public Label report1Label;

    /** contact report label */
    @FXML public Label report2Label;

    /** customer report label */
    @FXML public Label report3Label;

    /** type and month report button */
    @FXML public Button report1Button;

    /** contact report button */
    @FXML public Button report2Button;

    /** customer report button */
    @FXML public Button report3Button;

    /** back button */
    @FXML public Button backButton;

    /** button for combined type and month report */
    @FXML public Button report1Button2;

    /**
     * This method goes to the first report
     * @param actionEvent event
     * @throws IOException error
     */
    public void onActionGenReport1(ActionEvent actionEvent) throws IOException {

        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/TypeMonthReportView.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * This method goes to the second report
     * @param actionEvent event
     * @throws IOException error
     */
    public void onActionGenReport2(ActionEvent actionEvent) throws IOException {

        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/ContactReportView.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * This method goes to the third report
     * @param actionEvent event
     * @throws IOException error
     */
    public void onActionGenReport3(ActionEvent actionEvent) throws IOException {

        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/CustomerReportView.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * This method goes back a screen
     * @param actionEvent event
     * @throws IOException error
     */
    public void onActionBack(ActionEvent actionEvent) throws IOException {

        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Object scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/MainScreen.fxml")));
        stage.setScene(new Scene ((Parent)scene));
        stage.show();
    }

    /**
     * This method goes to the combined type and month report
     * @param actionEvent event
     * @throws IOException error
     */
    public void onActionGenTMCombo(ActionEvent actionEvent) throws IOException {

        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/TypeMonthComboReport.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }
}
