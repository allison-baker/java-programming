// Project Prolog
// Name: Al Baker
// CS3250 Section 601
// Project: Assignment 14
// Date: 12/09/2024
// Purpose: Control the functionality of the Java Image Machine.

package com.almariebaker.assignment14;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Files;

public class JIMController {
    @FXML private ScrollPane galleryScroll;
    @FXML private TilePane imageGallery;
    @FXML private BorderPane chosenImageView;
    @FXML private ImageView currentImage;
    @FXML private Label imageTitle;
    @FXML private Label captionLabel;
    @FXML private TextField caption;
    @FXML private Label error;
    private Stage stage;

    public void setStage(Stage stage) { this.stage = stage; }

    @FXML
    protected void onOpenButtonClick() throws MalformedURLException{
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File directory = directoryChooser.showDialog(stage);
        if (directory != null) {
            JIMachine.populateData(directory);
            currentImage.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                if (mouseEvent.getClickCount() > 1) { toAlbumView(); }
            });
            loadImages();
        }
    }

    private void loadImages() {
        galleryScroll.toFront();
        chosenImageView.toBack();
        imageGallery.getChildren().clear();
        for (int i = 0; i < JIMachine.imageList.size(); i++) {
            ImageView imageView = new ImageView(JIMachine.imageList.get(i));
            imageView.setId(String.valueOf(i));
            imageView.setFitWidth(240);
            imageView.setFitHeight(240);
            imageView.setPreserveRatio(true);
            imageView.setSmooth(true);
            imageView.setCache(true);
            imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                chosenImageView.toFront();
                galleryScroll.toBack();
                String index = mouseEvent.getPickResult().getIntersectedNode().getId();
                Image currImage = JIMachine.imageList.get(Integer.parseInt(index));
                currentImage.preserveRatioProperty().set(true);
                currentImage.setSmooth(true);
                currentImage.setCache(true);
                currentImage.setImage(currImage);
                currentImage.setId(index);
                imageTitle.setText(currImage.getUrl().substring(currImage.getUrl().lastIndexOf("/") + 1));
                captionLabel.setText(JIMachine.captions.get(currImage));
            });
            imageGallery.getChildren().add(imageView);
        }
    }

    @FXML
    protected void enterKeyCheck(KeyEvent keyEvent) { if (keyEvent.getCode() == KeyCode.ENTER) { addCaption(); } }

    @FXML
    protected void addCaption() {
        JIMachine.captions.replace(currentImage.getImage(), caption.getText());
        captionLabel.setText(caption.getText());
        caption.setText("");
    }

    @FXML
    protected void onInButtonClick() {
        currentImage.setScaleX(currentImage.getScaleX() * 1.25);
        currentImage.setScaleY(currentImage.getScaleY() * 1.25);
    }

    @FXML
    protected void onResetButtonClick() {
        currentImage.setScaleX(1.0);
        currentImage.setScaleY(1.0);
    }

    @FXML
    protected void onOutButtonClick() {
        currentImage.setScaleX(currentImage.getScaleX() * 0.75);
        currentImage.setScaleY(currentImage.getScaleY() * 0.75);
    }

    @FXML
    protected void onSaveButtonClick() {
        File newDirectory = new DirectoryChooser().showDialog(stage);
        JIMachine.saveAlbum(newDirectory);
    }

    @FXML
    protected void toAlbumView() {
        caption.setText("");
        galleryScroll.toFront();
        chosenImageView.toBack();
    }

    @FXML
    protected void onPrevButtonClick() {
        caption.setText("");
        int index = Integer.parseInt(currentImage.getId());
        if (index > 0) {
            error.setText("");
            currentImage.setImage(JIMachine.imageList.get(index - 1));
            imageTitle.setText(JIMachine.imageList.get(index - 1).getUrl().substring(JIMachine.imageList.get(index - 1).getUrl().lastIndexOf("/") + 1));
            captionLabel.setText(JIMachine.captions.get(JIMachine.imageList.get(index - 1)));
            currentImage.setId(String.valueOf(index - 1));
        } else {
            error.setText("No previous image.");
        }
    }

    @FXML
    protected void onNextButtonClick() {
        caption.setText("");
        int index = Integer.parseInt(currentImage.getId());
        if (index < JIMachine.imageList.size() - 1) {
            error.setText("");
            currentImage.setImage(JIMachine.imageList.get(index + 1));
            imageTitle.setText(JIMachine.imageList.get(index + 1).getUrl().substring(JIMachine.imageList.get(index + 1).getUrl().lastIndexOf("/") + 1));
            captionLabel.setText(JIMachine.captions.get(JIMachine.imageList.get(index + 1)));
            currentImage.setId(String.valueOf(index + 1));
        } else {
            error.setText("No next image.");
        }
    }

    @FXML
    protected void onQuitButtonClick() { stage.close(); }
}