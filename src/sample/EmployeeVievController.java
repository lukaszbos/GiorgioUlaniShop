package sample;

import com.jfoenix.controls.JFXTextField;
import database.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeVievController {

    @FXML
    private JFXTextField peselField;
    @FXML
    private JFXTextField surnameField;
    @FXML
    private JFXTextField positionField;
    @FXML
    private JFXTextField nameField;
    @FXML
    private JFXTextField dateField;
    @FXML
    private JFXTextField adressField;
    @FXML
    private JFXTextField telField;

    @FXML
    void initialize() {
        ResultSet rs = null,rs2 = null;
        Connection connection = null;

        ResultSet value = null;



        try {
            connection = DBConnection.getConnection(connection);
            value = DBConnection.getColumn("SELECT * from Pracownicy",connection);
           // rs2 = DBConnection.getColumn("SELECT NAZWISKO from Pracownicy",connection,rs);

            nameField.setText(DBConnection.getField(rs, 1));
           // surnameField.setText(DBConnection.getField(rs, 1));
           // dateField.setText(DBConnection.getField(rs, 4));
          //  peselField.setText(DBConnection.getField(rs, 5));
           // telField.setText(DBConnection.getField(rs, 6));

            //adres musi byc wyuskany inaczej
            // adressField.setStyle("-fx-text-inner-color: white");

            positionField.setStyle("-fx-text-inner-color: white");

        }catch (SQLException ee){
            System.out.println("blad w klase EVC2: " + ee);

        }

        nameField.setStyle("-fx-text-inner-color: white");
        peselField.setStyle("-fx-text-inner-color: white");
        surnameField.setStyle("-fx-text-inner-color: white");
        positionField.setStyle("-fx-text-inner-color: white");
        dateField.setStyle("-fx-text-inner-color: white");
        adressField.setStyle("-fx-text-inner-color: white");
        telField.setStyle("-fx-text-inner-color: white");


    }

    public void onLogOutClicked(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader();
        Parent root = null;

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();




        try {
            root = loader.load(getClass().getResource("sample.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Nie udalo sie wylogowac");
        }
        stage.setScene(new Scene(root));



    }
}
