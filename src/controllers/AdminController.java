package controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class AdminController extends Controller {
    @FXML
    private JFXButton employeeButton;
    @FXML
    private JFXButton logoutButton;
    @FXML
    private JFXButton produktyButton;

    @FXML
    void initialize() {
    }

    @FXML
    void onLogoutClicked(ActionEvent actionEvent) {
        setScene(actionEvent, attributes.Strings.LOGIN_SAMPLE_PATH);
    }

    @FXML
    void onProductsClicked(ActionEvent actionEvent) {
        setScene(actionEvent, attributes.Strings.PRODUCTS_SAMPLE_PATH);
    }

    @FXML
    void onEmployeeClicked(ActionEvent event) {
    }
}

