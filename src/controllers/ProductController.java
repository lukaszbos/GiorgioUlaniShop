package controllers;

import attributes.Enums;
import attributes.Strings;
import database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Klasa kontrolujaca wyswietlanie produktow
 */
public class ProductController extends Controller implements Initializable {

    @FXML
    private JFXButton deleteButton;
    @FXML
    private JFXButton confirmButton;
    @FXML
    private JFXButton refreshButton;
    @FXML
    private JFXButton backButton;
    @FXML
    private JFXButton addButton;
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

//    @FXML
//    public void selected(ActionEvent e)throws IOException,SQLException{
//
//        ObservableList<ModelProductTable> selected;
//        selected = table.getSelectionModel().getSelectedItems();
//        for (ModelProductTable modelProductTable : selected) {
//            int id = modelProductTable.getId();
//            // do whatever you need with id...
//        }
//    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        String sqlProducts = "SELECT PRODUKTY.NAZWA AS PNAZWA, PRODUKTY.CENA, PRODUKTY.DATA_WAZNOSCI, S.NAZWA AS SNAZWA, R.RODZAJ FROM PRODUKTY\n" +
                "                INNER JOIN SKLEPY S\n" +
                "                  on PRODUKTY.IDSKLEPU = S.IDSKLEPU\n" +
                "                INNER JOIN RODZAJE_PRODUKTOW R\n" +
                "                  on PRODUKTY.IDRODZAJPRODUKTU = R.IDRODZAJPRODUKTU";

        try {
            Connection connection = DBConnection.getConnection(null);
            ResultSet rsProducts = connection.createStatement().executeQuery(sqlProducts);


            while (rsProducts.next()) {
                observableList.add(new ModelProductTable(
                        rsProducts.getString("PNAZWA"),
                        rsProducts.getString("CENA"),
                        rsProducts.getString("RODZAJ"),
                        rsProducts.getString("SNAZWA"),
                        rsProducts.getString("DATA_WAZNOSCI")

                ));

            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Something went wrong! Products weren't retrived: ");
        }


        // name, price, type, shop, data;
        //propertyValueFactory
        //Creates a default PropertyValueFactory to extract the value from a given
        //TableView row item reflectively, using the given property name.
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colShop.setCellValueFactory(new PropertyValueFactory<>("shop"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("data"));

        table.setItems(observableList);
        /**
         * Robimy edytowalnosc tablicy produktow
         */
        table.setEditable(true); // ustawiamy edytowalnosc kolumn
        colName.setCellFactory(TextFieldTableCell.forTableColumn());
        colPrice.setCellFactory(TextFieldTableCell.forTableColumn());
        colType.setCellFactory(TextFieldTableCell.forTableColumn());
        colShop.setCellFactory(TextFieldTableCell.forTableColumn());
        colDate.setCellFactory(TextFieldTableCell.forTableColumn());

    }

    @FXML
    void onBackClicked(ActionEvent actionEvent) {
        if (Enums.typKonta == Enums.TypKonta.URSER)
            setScene(actionEvent, Strings.EMPLOYEE_SAMPLE_PATH);
        else if (Enums.typKonta == Enums.TypKonta.ADMIN)
            setScene(actionEvent, Strings.ADMIN_SAMPLE_PATH);
        else {
            System.out.println("Blad w klasie ProductControler, on Back Clicked");
        }
    }


    @FXML
    void onAddClicked(ActionEvent event) {
        if (Enums.typKonta == Enums.TypKonta.URSER) {
            JOptionPane.showMessageDialog(null," BRAK UPRAWNIEN ADMINISTRATORA","Failure",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    @FXML
    void onDeleteClicked(ActionEvent event) {
        if (Enums.typKonta == Enums.TypKonta.URSER) {
            JOptionPane.showMessageDialog(null," BRAK UPRAWNIEN ADMINISTRATORA","Failure",
                    JOptionPane.ERROR_MESSAGE);
        }

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

        // btw it is way better to keep the connection open while the app is running,
        // and just close it when the app shuts down....

        // the following "try with resources" at least makes sure things are closed:

        try {
                Connection connection = DBConnection.getConnection(null);
                // ResultSet rsProducts = connection.createStatement().executeQuery(sqlProducts);
                String sqlUpdate = "    UPDATE PRODUKTY\n" +
                        "    SET PRODUKTY.NAZWA = 'Cytrulina'\n" +
                        "    WHERE Produkty.IDPRODUKTU = 1";
               // PreparedStatement stmt = connection.prepareStatement("UPDATE PRODUKTY SET "+column+" = ? WHERE IDPRODUKTU = ? ");
                PreparedStatement stmt = connection.prepareStatement(sqlUpdate);

            //   ResultSet rsProducts = connection.createStatement().executeQuery(sqlProducts);
     //   ) {

           // stmt.setString(1, newValue);
            System.out.println("new value: " + newValue);
          //  stmt.setString(2, id);
            System.out.println("id: " + id);
            stmt.execute();
        } catch (SQLException ex) {
            System.err.println("Error in udpating view table - product controller");
            // if anything goes wrong, you will need the stack trace:
            ex.printStackTrace(System.err);
        }
    }

    @FXML
    void onConfirmClicked(ActionEvent actionEvent) {
        System.out.println("klikam confirm ");
        
        ObservableList<ModelProductTable> selected;
        selected = table.getSelectionModel().getSelectedItems();
        int id = 1;
        for (ModelProductTable modelProductTable : selected) {
            id = modelProductTable.getId();
            // do whatever you need with id...
        }

        Connection connection = DBConnection.getConnection(null);
        // ResultSet rsProducts = connection.createStatement().executeQuery(sqlProducts);
     //   String sqlUpdate = "    UPDATE PRODUKTY\n" +
       //         "    SET PRODUKTY.NAZWA = 'Cytrulina'\n" +
         //       "    WHERE Produkty.IDPRODUKTU = 1";
        // PreparedStatement stmt = connection.prepareStatement("UPDATE PRODUKTY SET PRODUKTY.NAZWA = 'Cytrulina' WHERE Produkty.IDPRODUKTU = 1");
        try {
            PreparedStatement stmt = connection.prepareStatement("UPDATE PRODUKTY SET PRODUKTY.NAZWA = 'Cytrulina' WHERE Produkty.IDPRODUKTU = 1");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        colName.getText();
        ModelProductTable modelProductTable = null;
       // updateData("Nazwa", "Cytrulina", "0");

///        modelProductTable.getId();
//        updateData("PNAZWA", colName.getText(), colName.getId());

      //  System.out.println("Event value: " + event.getNewValue());
//        System.out.println("Get id value: " + modelProductTable.getId());


//        Button update = new Button("Update");
//        update.setOnAction(event -> {
//            id.getT
//            System.out.println("wchodze do labmdy ");
//            ModelProductTable modelProductTable = event.getRowValue();
//            modelProductTable.setName(event.getNewValue());
//            updateData("PNAZWA", event.getNewValue(), modelProductTable.getId());
//            System.out.println("Event value: " + event.getNewValue());
//            System.out.println("Get id value: " + modelProductTable.getId());
//        });

    }



    /*
    private void updateData(String column, String newValue, String id) throws SQLException {
        Connection connection = DBConnection.getConnection(null);
        // ResultSet rsProducts = connection.createStatement().executeQuery(sqlProducts);
        PreparedStatement stmt = connection.prepareStatement("UPDATE PRODUKTY SET "+column+" = ? WHERE IDPRODUKTU = ? ");


        Button update = new Button("Update");
      //  update.setFont(Font.font("SanSerif", 15));
        update.setOnAction(e -> {
           // if (validateNumber() & validateFirstName() & validateLastName() & validateEmaill() & validateMobileNo() & validatePassword() & validateFields()) {
                try {
                    //String query = "update UserDatabase set ID=?, FirstName=?, LastName=?, Email=?, Username=?, Password=?, DOB=?, Gender=?, MobileNo=?, Hobbies=?, Image=? where ID='" + id.getText() + "' ";
                    //stmt = conn.prepareStatement(query);

                    stmt.setString(1, newValue);


                   // fis = new FileInputStream(file);
                  //  stmt.setBinaryStream(11, (InputStream) fis, (int) file.length());

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("User details has been updated.");
                    alert.showAndWait();

                    stmt.execute();

                    stmt.close();
                 //   clearFields();
                } catch (SQLException e1) {
                    //label.setText("SQL Error");
                    System.err.println(e1);
                }

           // }
        });


    }
*/

}