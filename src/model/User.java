package model;

import javafx.scene.control.Alert;
import java.util.Locale;

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
     * changes dialog based on the system language
     */
    public static void incorrectUserNameOrPassword(){

        if(Locale.getDefault().getLanguage().equals("fr")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Nom d’utilisateur ou mot de passe incorrect");
            alert.setContentText("Vous avez entré des informations d’identification incorrectes. Veuillez réessayer!");
            alert.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incorrect UserName or Password");
            alert.setContentText("You have entered incorrect credentials. Please try again!");
            alert.showAndWait();
        }
    }

    /**
     * generates an error dialog box for no username being entered by the user
     * changes dialog based on the system language
     */
    public static void blankUsername() {

        if(Locale.getDefault().getLanguage().equals("fr")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Aucun nom d’utilisateur entré");
            alert.setContentText("Veuillez entrer un nom d’utilisateur!");
            alert.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Username Entered");
            alert.setContentText("Please enter a username!");
            alert.showAndWait();
        }
    }

    /**
     * generates an error dialog box for no password being entered by the user
     * changes dialog based on the system language
     */
    public static void blankPassword() {

        if(Locale.getDefault().getLanguage().equals("fr")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Mot de passe omis");
            alert.setContentText("veuillez entrer un mot de passe!");
            alert.showAndWait();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Password Omitted");
            alert.setContentText("Please enter a password!");
            alert.showAndWait();
        }
    }
}
