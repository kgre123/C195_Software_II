package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class AdminCustomerView implements Initializable {

    Parent scene;

    @FXML
    public TableView customerTable;

    @FXML
    public TableColumn customerIdColumn;

    @FXML
    public TableColumn nameColumn;

    @FXML
    public TableColumn addressColumn;

    @FXML
    public TableColumn postalCodeColumn;

    @FXML
    public TableColumn phoneNumberColumn;

    @FXML
    public TableColumn flDivisionColumn;

    @FXML
    public TableColumn countryDataColumn;

    @FXML
    public Button addCustomerButton;

    @FXML
    public Button modifyCustomerButton;

    @FXML
    public Button deleteCustomerButton;

    @FXML
    public Button exitButton;

    @FXML
    public Label customerLabel;

    @FXML
    public Button backButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /**
     * This method takes the user to the form to add a customer
     *
     * @param actionEvent when the button is clicked
     * @throws IOException error
     */
    public void onActionAddCustomer(ActionEvent actionEvent) throws IOException {

        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/AdminAddCustomer.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * This method takes the user to the form to modify a customer with information from the selected customer
     *
     * @param actionEvent when the button is clicked
     * @throws IOException error
     */
    public void onActionModifyCustomer(ActionEvent actionEvent) throws IOException {

        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/AdminModifyCustomer.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void onActionDeleteCustomer(ActionEvent actionEvent) {
    }

    /**
     * This method exits the program.
     * @param actionEvent when the button is clicked
     */
    public void onActionExitProgram(ActionEvent actionEvent) {
        System.exit(0);
    }

    /**
     * This method takes the user to the screen to select a customer or appointment
     *
     * @param actionEvent when the button is clicked
     * @throws IOException error
     */
    public void onActionGoBack(ActionEvent actionEvent) throws IOException {

        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/AdminFirstScreen.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }
}
