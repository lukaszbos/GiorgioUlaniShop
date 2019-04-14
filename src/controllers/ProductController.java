package controllers;

import attributes.Enums;
import attributes.Strings;
import database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

//klasa do wyrzucenia
public class ProductController extends Controller implements Initializable {
    @FXML
    private TableView<ModelProductTable> table;
    @FXML
    private TableColumn<ModelProductTable, String> colShop;
    @FXML
    private TableColumn<ModelProductTable, String> colDate;
    @FXML
    private TableColumn<ModelProductTable, String> colName;
    @FXML
    private TableColumn<ModelProductTable, String> colPrice;
    @FXML
    private TableColumn<ModelProductTable, String> colType;
    //ta lista umozliwia sledzenie zmian jesli sie pojawia
    ObservableList<ModelProductTable> observableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String sqlProducts = "SELECT PRODUKTY.NAZWA AS PNAZWA, PRODUKTY.CENA, PRODUKTY.DATA_WAZNOSCI, S.NAZWA AS SNAZWA, R.RODZAJ FROM PRODUKTY\n" +
                "                INNER JOIN SKLEPY S\n" +
                "                  on PRODUKTY.IDSKLEPU = S.IDSKLEPU\n" +
                "                INNER JOIN RODZAJE_PRODUKTOW R\n" +
                "                  on PRODUKTY.IDRODZAJPRODUKTU = R.IDRODZAJPRODUKTU";
        renderProducts(sqlProducts);
        setProductsTable();
    }

    private void renderProducts(String sqlProducts) {
        try {
            connectToServerAndGetProductsFromDatabase(sqlProducts);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Something went wrong! Products weren't retrived: ");
        }
    }

    private void connectToServerAndGetProductsFromDatabase(String sqlProducts) throws SQLException {
        Connection connection = DBConnection.getConnection(null);
        ResultSet rsProducts = connection.createStatement().executeQuery(sqlProducts);
        while (isNextProduct(rsProducts)) {
            observableList.add(getProductParametersAndSetThemToNewObject(rsProducts));
        }
    }

    private boolean isNextProduct(ResultSet rsProducts) throws SQLException {
        return rsProducts.next();
    }

    private ModelProductTable getProductParametersAndSetThemToNewObject(ResultSet rsProducts) throws SQLException {
        return new ModelProductTable(
                rsProducts.getString("PNAZWA"),
                rsProducts.getString("CENA"),
                rsProducts.getString("RODZAJ"),
                rsProducts.getString("SNAZWA"),
                rsProducts.getString("DATA_WAZNOSCI")
        );
    }

    private void setProductsTable() {
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colShop.setCellValueFactory(new PropertyValueFactory<>("shop"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("data"));
        table.setItems(observableList);
        table.setEditable(true); // ustawiamy edytowalnosc kolumn
        colName.setCellFactory(TextFieldTableCell.forTableColumn());
        colPrice.setCellFactory(TextFieldTableCell.forTableColumn());
        colType.setCellFactory(TextFieldTableCell.forTableColumn());
        colShop.setCellFactory(TextFieldTableCell.forTableColumn());
        colDate.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    @FXML
    void onBackClicked(ActionEvent actionEvent) {
        if (isTypeOfAccount(getUrser()))
            setScene(actionEvent, Strings.EMPLOYEE_SAMPLE_PATH);
        else if (isTypeOfAccount(getAdmin()))
            setScene(actionEvent, Strings.ADMIN_SAMPLE_PATH);
        else {
            System.out.println("Blad w klasie ProductControler, on Back Clicked");
        }
    }

    private Enums.TypKonta getAdmin() {
        return Enums.TypKonta.ADMIN;
    }

    private Enums.TypKonta getUrser() {
        return Enums.TypKonta.URSER;
    }

    private boolean isTypeOfAccount(Enums.TypKonta urser) {
        return Enums.typKonta == urser;
    }

    @FXML
    void onAddClicked(ActionEvent event) {
        showNoAccessWarning(); // method from Controller
    }

    @FXML
    void onDeleteClicked(ActionEvent event) {
        showNoAccessWarning(); // method from Controller
    }

    public void onEditName(TableColumn.CellEditEvent<ModelProductTable, String> modelProductTableStringCellEditEvent) {
        ModelProductTable productTable = table.getSelectionModel().getSelectedItem();
        productTable.setName(modelProductTableStringCellEditEvent.getNewValue());
    }

    public void onEditPrice(TableColumn.CellEditEvent<ModelProductTable, String> modelProductTableStringCellEditEvent) {
        ModelProductTable productTable = table.getSelectionModel().getSelectedItem();
        productTable.setPrice(modelProductTableStringCellEditEvent.getNewValue());
    }

    public void onEditType(TableColumn.CellEditEvent<ModelProductTable, String> modelProductTableStringCellEditEvent) {
        ModelProductTable productTable = table.getSelectionModel().getSelectedItem();
        productTable.setType(modelProductTableStringCellEditEvent.getNewValue());
    }

    public void onEditShop(TableColumn.CellEditEvent<ModelProductTable, String> modelProductTableStringCellEditEvent) {
        ModelProductTable productTable = table.getSelectionModel().getSelectedItem();
        productTable.setShop(modelProductTableStringCellEditEvent.getNewValue());
    }

    public void onEditDate(TableColumn.CellEditEvent<ModelProductTable, String> modelProductTableStringCellEditEvent) {
        ModelProductTable productTable = table.getSelectionModel().getSelectedItem();
        productTable.setDate(modelProductTableStringCellEditEvent.getNewValue());
    }


    private void updateData(String column, String newValue, String id) {
        try {
            updateProduct();
        } catch (SQLException ex) {
            System.err.println("Error in udpating view table - product controller");
            ex.printStackTrace(System.err);
        }
    }

    private void updateProduct() throws SQLException {
        Connection connection = DBConnection.getConnection(null);
        // ResultSet rsProducts = connection.createStatement().executeQuery(sqlProducts);
        String sqlUpdate = "    UPDATE PRODUKTY\n    SET PRODUKTY.NAZWA = 'Cytrulina'\n    WHERE Produkty.IDPRODUKTU = 1";
        PreparedStatement stmt = connection.prepareStatement(sqlUpdate);
        stmt.execute();
    }

    @FXML
    void onConfirmClicked(ActionEvent actionEvent) {
        ObservableList<ModelProductTable> selected;
        selected = table.getSelectionModel().getSelectedItems();
        int id = 1;
        for (ModelProductTable modelProductTable : selected) {
            id = modelProductTable.getId();
        }
        Connection connection = DBConnection.getConnection(null);
        getProduct(connection);
        colName.getText();
        ModelProductTable modelProductTable = null;
    }

    private void getProduct(Connection connection) {
        try {
            PreparedStatement stmt = connection.prepareStatement("UPDATE PRODUKTY SET PRODUKTY.NAZWA = 'Cytrulina' WHERE Produkty.IDPRODUKTU = 1");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}