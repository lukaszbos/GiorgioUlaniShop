package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * Every controller extends this class
 */
abstract class Controller {


    /**
     * Thanks to this method you are able to see pictures in program
     *
     * @param imagesPath - Path to image that you want to show
     * @param imgView    - Created in Scene Builder variable
     */
    void setImage(String imagesPath, ImageView imgView) {
        try {
            Image image = new Image(imagesPath);
            imgView.setImage(image);
            imgView.setCache(true);

        } catch (Exception e) {
            System.out.println("Blad z ustawianiem image: " + "Path: " + imagesPath + "Exception " + e);
        }
    }

    /**
     * Method for changing Scenes
     *
     * @param actionEvent -
     * @param samplePath  - Path to the next scene
     */
    void setScene(ActionEvent actionEvent, String samplePath) {
        FXMLLoader loader = new FXMLLoader();
        Parent root = null;
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        try {
            root = loader.load(Objects.requireNonNull(getClass().getClassLoader().getResource(samplePath)));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Nie udalo sie zmienic sceny: " + samplePath);
        }
        assert root != null;
        stage.setScene(new Scene(root));
    }

}
