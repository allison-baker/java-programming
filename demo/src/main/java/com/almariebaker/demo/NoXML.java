package com.almariebaker.demo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.text.Text;
import javafx.scene.text.Font;

import java.io.IOException;

public class NoXML extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Group root = new Group();
        Scene scene = new Scene(root, 640, 400);

        Text text = new Text();
        text.setText("Hello, JavaFX!");
        text.setX(25.0);
        text.setY(75.0);
        text.setFont(Font.font(50.0));
        text.setFill(Color.PINK);
        root.getChildren().add(text);

        Line line = new Line();
        line.setStartX(25.0);
        line.setStartY(85.0);
        line.setEndX(325.0);
        line.setEndY(85.0);
        line.setStrokeWidth(5.0);
        line.setStroke(Color.LAVENDER);
        root.getChildren().add(line);

        Rectangle rectangle = new Rectangle();
        rectangle.setX(25.0);
        rectangle.setY(180.0);
        rectangle.setWidth(350.0);
        rectangle.setHeight(100.0);
        rectangle.setFill(Color.BLUEVIOLET);
        root.getChildren().add(rectangle);

        TextField textField = new TextField();
        textField.setLayoutX(25.0);
        textField.setLayoutY(100.0);
        root.getChildren().add(textField);

        Button submit = new Button();
        submit.setText("Submit");
        submit.setOnAction(event -> {
            String input = textField.getText();
            int newY = Integer.parseInt(input);
            rectangle.setY(newY);
        });
        submit.setLayoutX(25.0);
        submit.setLayoutY(140.0);
        root.getChildren().add(submit);

        stage.setTitle("JavaFX No XML Sandbox");
        stage.setScene(scene);
        stage.show();
    }

    public static void main (String[] args) { launch(); }
}
