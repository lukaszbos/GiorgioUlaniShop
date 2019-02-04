package controllers;

import attributes.Strings;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.util.Objects;


public class Main extends Application {
    Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(Objects.requireNonNull(getClass().getClassLoader().getResource(Strings.LOGIN_SAMPLE_PATH)));

        stage.setTitle("GIORGIO ULANI");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
