package com.almariebaker.assignment10;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.net.MalformedURLException;

public class JIMController {
    @FXML private ImageView imageView;
    private Stage myStage;

    public void setStage(Stage stage) { myStage = stage; }

    @FXML
    protected void onOpenButtonClick() throws MalformedURLException{
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif", "*.bmp"));
        File file = fileChooser.showOpenDialog(myStage);
        if (file != null) {
            Image image = new Image(file.toURI().toURL().toExternalForm());
            imageView.preserveRatioProperty().set(true);
            imageView.setSmooth(true);
            imageView.setCache(true);
            imageView.setImage(image);
        }
    }

    @FXML
    protected void onInButtonClick() {
        imageView.setScaleX(imageView.getScaleX() * 1.25);
        imageView.setScaleY(imageView.getScaleY() * 1.25);
    }

    @FXML
    protected void onResetButtonClick() {
        imageView.setScaleX(1.0);
        imageView.setScaleY(1.0);
    }

    @FXML
    protected void onOutButtonClick() {
        imageView.setScaleX(imageView.getScaleX() * 0.75);
        imageView.setScaleY(imageView.getScaleY() * 0.75);
    }

    @FXML
    protected void onQuitButtonClick() { myStage.close(); }
}