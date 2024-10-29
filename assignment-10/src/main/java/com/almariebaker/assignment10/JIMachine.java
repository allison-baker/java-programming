package com.almariebaker.assignment10;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class JIMachine extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(JIMachine.class.getResource("java-image-loader.fxml"));
        Parent parent = fxmlLoader.load();
        JIMController controller = fxmlLoader.getController();
        controller.setStage(stage);
        Scene scene = new Scene(parent, 1000, 700);
        stage.setTitle("Java Image Loader");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}