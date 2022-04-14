package main;

import helper.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        /*Parent root = FXMLLoader.load(getClass().getResource("/view/LoginScreen.fxml"));
        stage.setTitle("User Login");
        stage.setScene(new Scene(root, 300, 275));
        stage.show(); */

        Parent root = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
        stage.setTitle("Main Screen");
        stage.setScene(new Scene(root, 300, 275));
        stage.show();
    }


    public static void main(String[] args) {

        JDBC.startConnection();
        launch(args);

        JDBC.closeConnection();
    }
}
