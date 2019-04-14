package controllers;

import attributes.Enums;
import attributes.Strings;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import others.User;

import javax.swing.*;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginController extends Controller implements Initializable {
    private Enums enums = new Enums();
    private User simpleUser = new User("Michal", "admin");
    private User admin = new User("admin", "admin");

    @FXML
    private ImageView logoGU;
    @FXML
    private ImageView passwordIcon;
    @FXML
    private ImageView userIcon;
    @FXML
    private JFXButton loginButton;
    @FXML
    private JFXTextField userText;
    @FXML
    private JFXPasswordField paswordText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //setting images on login scene
        setImagesOnLoginScreen();
        adjustFonts();
    }

    private void setImagesOnLoginScreen() {
        setImageFromPathToView(Strings.LOGO, logoGU);
        setImageFromPathToView(Strings.USER_ICON, userIcon);
        setImageFromPathToView(Strings.PASS_ICON, passwordIcon);
    }

    private void adjustFonts() {
        userText.setStyle("-fx-text-inner-color: white");
        paswordText.setStyle("-fx-text-inner-color: white");
    }

    @FXML
    void initialize() {
    }

    @FXML
    public void onLoginClicked(ActionEvent actionEvent) {
        String userName = userText.getText();
        String password = paswordText.getText();
        User userTryingToLogIn = new User(userName, password);

        if (isUserValid(userTryingToLogIn, simpleUser)) {
            logAsSimpleUser(actionEvent);
        } else if (isUserValid(userTryingToLogIn, admin)) {
            logAsAdmin(actionEvent);
        } else {
            showInvalidPassesMessage();
        }
    }

    private boolean isUserValid(User userTryingToLogIn, User user) {
        return userTryingToLogIn.equals(user);
    }

    private void logAsSimpleUser(ActionEvent actionEvent) {
        enums.setTypKonta(Enums.TypKonta.URSER);
        setScene(actionEvent, Strings.EMPLOYEE_SAMPLE_PATH);
        //System.out.println(userName);
    }

    private void logAsAdmin(ActionEvent actionEvent) {
        enums.setTypKonta(Enums.TypKonta.ADMIN);
        setScene(actionEvent, Strings.ADMIN_SAMPLE_PATH);
    }

    private void showInvalidPassesMessage() {
        JOptionPane.showMessageDialog(null, " Nieprawidlowe haslo lub login!", "Failure", JOptionPane.ERROR_MESSAGE);
    }

    @FXML
    public void onSignUpButtonClicked(ActionEvent actionEvent) {
    }
}