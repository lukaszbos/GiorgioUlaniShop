package controllers;

import attributes.Strings;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import database.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeVievController extends Controller {

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
    private JFXTextField zipCodeField;
    @FXML
    private JFXTextField cityField;
    @FXML
    private JFXTextField streetField;
    @FXML
    private JFXTextField houseNumberField;
    @FXML
    private JFXTextField apartmentNumberField;
    @FXML
    private JFXTextField telField;

    @FXML
    private JFXButton historyButton;
    @FXML
    private JFXButton graphicButton;
    @FXML
    private JFXButton logOutButton;
    @FXML
    private JFXButton showButton;
    @FXML
    private JFXButton changeData;


    @FXML
    void initialize() {
        adjustStyle();
        setEmployeeData();

    }

    private void setEmployeeData() {
        ResultSet rs;
        ResultSet rsPosition;
        ResultSet rsAdres;
        Connection connection;

        connection = DBConnection.getConnection(null);
        rs = DBConnection.getColumn("SELECT * from Pracownicy", connection);

        int adresId;
        int positionId;
        try {
            while (rs.next()) {
                System.out.println("wartosc kolumny: " + rs.getInt(1));
                nameField.setText(rs.getString("IMIE"));
                surnameField.setText(rs.getString("NAZWISKO"));
                dateField.setText(rs.getString("DATA_ZATRUDNIENIA"));
                peselField.setText(rs.getString("PESEL"));
                telField.setText(rs.getString("NUMER_TELEFONU"));

                adresId = rs.getInt("IDADRESU");
                rsAdres = DBConnection.getColumn("SELECT * from Adresy WHERE IDADRESU=" + adresId, connection);
                while (rsAdres.next()) {
                    String fullAdres;
                    fullAdres = rsAdres.getString(3) + ", "
                            + rsAdres.getString(2) + ", "
                            + rsAdres.getString(4) + ", "
                            + rsAdres.getString(5);

                    if (rsAdres.getString(6) != null)
                        fullAdres += ("/ " + rsAdres.getString(6));

                    zipCodeField.setText(fullAdres);
                }


                positionId = rs.getInt("IDSTANOWISKA");
                rsPosition = DBConnection.getColumn("SELECT * from Stanowiska WHERE IDSTANOWISKA=" + positionId, connection);
                while (rsPosition.next())
                    positionField.setText(rsPosition.getString(2));

            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Problem with setting employee data");

        }

    }

    private void adjustStyle() {
        positionField.setStyle("-fx-text-inner-color: white");
        nameField.setStyle("-fx-text-inner-color: white");
        peselField.setStyle("-fx-text-inner-color: white");
        surnameField.setStyle("-fx-text-inner-color: white");
        positionField.setStyle("-fx-text-inner-color: white");
        dateField.setStyle("-fx-text-inner-color: white");
        zipCodeField.setStyle("-fx-text-inner-color: white");
        telField.setStyle("-fx-text-inner-color: white");
    }


    @FXML
    void onShowProductsClicked(ActionEvent actionEvent) {
        setScene(actionEvent, Strings.PRODUCTS_SAMPLE_PATH);
    }

    public void onLogOutClicked(ActionEvent actionEvent) {
        setScene(actionEvent, Strings.LOGIN_SAMPLE_PATH);
    }

    @FXML
    void onHistoryClicked(ActionEvent actionEvent) {
        noAccess(); // method from Controller

    }


    @FXML
    void onChangeDataClicked(ActionEvent actionEvent) {
        noAccess(); // method from Controller
    }

    public void onGraphicClicked(ActionEvent actionEvent) {
    }
}
