package main;

import dbConnections.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Kyle Green
 * This is the main file for the running of the application.
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/view/LoginScreen.fxml"));
        stage.setTitle("Appointment Management System");
        stage.setScene(new Scene(root, 300, 275));
        stage.show();

    }


    public static void main(String[] args) {

        JDBC.startConnection();
        launch(args);
        JDBC.closeConnection();
    }
}
