package sample;

import database.DBConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.sql.SQLException;
import java.sql.Statement;


public class Main extends Application {
    Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource("sample.fxml"));

        stage.setTitle("Hello World");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }


    public static void main(String[] args) throws SQLException {
        launch(args);




    }


}
