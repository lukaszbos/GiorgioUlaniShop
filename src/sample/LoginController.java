package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LoginController {

    @FXML
    private JFXButton loginButton;

    @FXML
    private JFXTextField userText;

    @FXML
    private JFXPasswordField paswordText;


    @FXML
    void initialize() {
        //loginButton.setText("o kurde");
        // userText.setOnAction(this::onLoginClicked);
        userText.setStyle("-fx-text-inner-color: white");
        paswordText.setStyle("-fx-text-inner-color: white");


    }

    @FXML
    public void onLoginClicked(ActionEvent actionEvent) {
        System.out.println("Zostales zalogowany: " + actionEvent);
        FXMLLoader loader = new FXMLLoader();
        Parent root = null;

        String userName = userText.getText();
        String password = paswordText.getText();

        String user1 = "Michal";
        String password1 = "1234";

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        try {
            if (true || (Objects.equals(userName, user1) && Objects.equals(password, password1))) {
                root = loader.load(getClass().getResource("sampleEmployee.fxml"));
                stage.setScene(new Scene(root));
                System.out.println(userName);


            } else {
                System.out.println("Error! Login or password is uncorrect");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void onSignUpButtonClicked(ActionEvent actionEvent) {
    }
}