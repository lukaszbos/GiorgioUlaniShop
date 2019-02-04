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

import javax.swing.*;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class LoginController extends Controller implements Initializable {

    /**
     * okreslenie typu konta Admin czy moze Zwykly uzytkownik
     */
    public Enums enums = new Enums();


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
        setImage(attributes.Strings.LOGO, logoGU);
        setImage(attributes.Strings.USER_ICON, userIcon);
        setImage(attributes.Strings.PASS_ICON, passwordIcon);

        userText.setStyle("-fx-text-inner-color: white");
        paswordText.setStyle("-fx-text-inner-color: white");
    }


    @FXML
    void initialize() {
    }


    @FXML
    public void onLoginClicked(ActionEvent actionEvent) {
       // System.out.println("Zostales zalogowany: " + actionEvent);

        String userName = userText.getText();
        String password = paswordText.getText();

        String user1 = "Michal";
        String password1 = "admin";
        String user2 = "admin";
        String password2 = "admin";


        if ((Objects.equals(userName, user1) && Objects.equals(password, password1))) {
            enums.setTypKonta(Enums.TypKonta.URSER);
            setScene(actionEvent, Strings.EMPLOYEE_SAMPLE_PATH);
           //System.out.println(userName);
        } else if (Objects.equals(userName, user2) && Objects.equals(password, password2)) {
            enums.setTypKonta(Enums.TypKonta.ADMIN);
            setScene(actionEvent, Strings.ADMIN_SAMPLE_PATH);
            //System.out.println(userName);
        } else {
            JOptionPane.showMessageDialog(null, " Nieprawidlowe haslo lub login!", "Failure", JOptionPane.ERROR_MESSAGE);
        }

    }

    @FXML
    public void onSignUpButtonClicked(ActionEvent actionEvent) {
    }


}