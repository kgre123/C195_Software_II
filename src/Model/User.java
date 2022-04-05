package Model;

import javafx.scene.control.Alert;

public class User {

    String userName;
    String password;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static void incorrectUserNameorPassword(){

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Incorrect UserName or Password");
        alert.setContentText("You have entered incorrect credentials. Please try again!");
        alert.showAndWait();
    }
}
