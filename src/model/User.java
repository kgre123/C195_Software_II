package model;

import javafx.scene.control.Alert;

public class User {

    private int userId;
    private String userName;
    private static String password;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    /**
     * @return the user id
     */
    public int getUserId(){
        return userId;
    }

    /**
     * @param userId setter
     */
    public void setUserId(int userId){
        this.userId = userId;
    }

    /**
     * @return the username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName setter
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the user password
     */
    public static String getPassword() {
        return password;
    }

    /**
     * @param password setter
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * generates an error dialog box for the username or password being incorrect
     */
    public static void incorrectUserNameOrPassword(){

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Incorrect UserName or Password");
        alert.setContentText("You have entered incorrect credentials. Please try again!");
        alert.showAndWait();
    }
}
