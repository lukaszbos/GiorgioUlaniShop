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

//ta klasa jest za długa
public class EmployeeViewController extends Controller {
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
        Connection connection = getConnection();
        setEmployeeData(connection);
        adjustStyleOfFont();
    }

    private Connection getConnection() {
        return DBConnection.getConnection(null);
    }

    private void setEmployeeData(Connection connection) {
        ResultSet resultOfSqlQuery;
        String sqlQuery = getSqlQuery();
        resultOfSqlQuery = getResultOfSqlQuery(connection, sqlQuery);
        tryToGetEmployeeData(resultOfSqlQuery, connection);
    }

    private String getSqlQuery() {
        return "SELECT * from Pracownicy";
    }

    private ResultSet getResultOfSqlQuery(Connection connection, String s) {
        ResultSet resultOfSqlQuery;
        resultOfSqlQuery = DBConnection.getColumn(s, connection);
        return resultOfSqlQuery;
    }

    private void tryToGetEmployeeData(ResultSet resultOfSqlQuery, Connection connection) {
        try {
            showTextOnScreen(resultOfSqlQuery, connection);
        } catch (SQLException e) {
            returnSettingDataError(e);
        }
    }

    private void showTextOnScreen(ResultSet resultOfSqlQuery, Connection connection) throws SQLException {
        while (isNextRecordAvailable(resultOfSqlQuery)) {
            System.out.println("wartosc kolumny: " + resultOfSqlQuery.getInt(1));
            setBasicData(resultOfSqlQuery);
            setAddress(resultOfSqlQuery, connection);
            setStanowisko(resultOfSqlQuery, connection);
        }
    }

    private void setBasicData(ResultSet resultOfSqlQuery) throws SQLException {
        nameField.setText(resultOfSqlQuery.getString("IMIE"));
        surnameField.setText(resultOfSqlQuery.getString("NAZWISKO"));
        dateField.setText(resultOfSqlQuery.getString("DATA_ZATRUDNIENIA"));
        peselField.setText(resultOfSqlQuery.getString("PESEL"));
        telField.setText(resultOfSqlQuery.getString("NUMER_TELEFONU"));
    }

    private void setAddress(ResultSet resultOfSqlQuery, Connection connection) throws SQLException {
        int addressId = resultOfSqlQuery.getInt("IDADRESU");
        ResultSet rsAddress = DBConnection.getColumn("SELECT * from Adresy WHERE IDADRESU=" + addressId, connection);
        while (isNextRecordAvailable(rsAddress)) {
            String fullAddress = getAddressFromDatabase(rsAddress);
            setAddress(fullAddress);
        }
    }

    private boolean isNextRecordAvailable(ResultSet rsAddress) throws SQLException {
        return rsAddress.next();
    }

    private String getAddressFromDatabase(ResultSet rsAddress) throws SQLException {
        String fullAddress = getFullAddressFromDatabase(rsAddress);
        if (zipCodeExist(rsAddress)) {
            fullAddress = attachZipCodeToAddress(rsAddress, fullAddress);
        }
        return fullAddress;
    }

    private String getFullAddressFromDatabase(ResultSet rsAddress) throws SQLException {
        return rsAddress.getString(3) + ", "
                + rsAddress.getString(2) + ", "
                + rsAddress.getString(4) + ", "
                + rsAddress.getString(5);
    }

    private boolean zipCodeExist(ResultSet rsAddress) throws SQLException {
        return rsAddress.getString(6) != null;
    }

    private String attachZipCodeToAddress(ResultSet rsAddress, String fullAddress) throws SQLException {
        fullAddress += ("/ " + rsAddress.getString(6));
        return fullAddress;
    }

    private void setAddress(String fullAddress) {
        zipCodeField.setText(fullAddress);
    }

    private void setStanowisko(ResultSet resultOfSqlQuery, Connection connection) throws SQLException {
        int positionId = resultOfSqlQuery.getInt("IDSTANOWISKA");
        ResultSet rsPosition = DBConnection.getColumn("SELECT * from Stanowiska WHERE IDSTANOWISKA=" + positionId, connection);
        while (isNextRecordAvailable(rsPosition)) {
            int positionArgument = 2;
            positionField.setText(rsPosition.getString(positionArgument));
        }
    }

    private void returnSettingDataError(SQLException e) {
        e.printStackTrace();
        System.out.println("Problem with setting employee data");
    }

    //this method should not be here
    private void adjustStyleOfFont() {
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
        String productSamplePath = Strings.PRODUCTS_SAMPLE_PATH;
        setScene(actionEvent, productSamplePath);
    }

    public void onLogOutClicked(ActionEvent actionEvent) {
        String loginSamplePath = Strings.LOGIN_SAMPLE_PATH;
        setScene(actionEvent, loginSamplePath);
    }

    @FXML
    void onHistoryClicked(ActionEvent actionEvent) {
        showNoAccessWarning(); // method from Controller
    }

    @FXML
    void onChangeDataClicked(ActionEvent actionEvent) {
        showNoAccessWarning(); // method from Controller
    }

    public void onGraphicClicked(ActionEvent actionEvent) {
    }
}
