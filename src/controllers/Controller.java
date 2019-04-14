package controllers;

import attributes.Enums;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.util.Objects;

abstract class Controller {
    Parent root;

    void setImageFromPathToView(String imagesPath, ImageView imgView) {
        Image imagesView = new Image(imagesPath);
        try {
            imgView.setImage(imagesView);
            imgView.setCache(true);
        } catch (Exception e) {
            System.out.println("Blad z ustawianiem imagesView: " + "Path: " + imagesPath + "Exception " + e);
        }
    }

    void setScene(ActionEvent actionEvent, String samplePath) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        root = getParentRoot(samplePath);
        assert root != null;
        stage.setScene(new Scene(root));
    }

    private Parent getParentRoot(String pathToSample) {
        Parent root = null;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource(pathToSample)));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Nie udalo sie zmienic sceny: " + pathToSample);
        }
        return root;
    }

    void showNoAccessWarning() {
        if (isAdmin())
            JOptionPane.showMessageDialog(null, " BRAK UPRAWNIEN ADMINISTRATORA", "Failure", JOptionPane.ERROR_MESSAGE);
    }

    private boolean isAdmin() {
        return Enums.typKonta != Enums.TypKonta.ADMIN;
    }
}