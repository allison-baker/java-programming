// Project Prolog
// Name: Al Baker
// CS3250 Section 601
// Project: Assignment 14
// Date: 12/09/2024
// Purpose: Launch the Java Image Machine application using FXML.

package com.almariebaker.assignment14;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class JIMachine extends Application {
    public static final String[] EXTENSIONS = new String[] {"jpg", "jpeg", "png", "gif", "bmp"};
    public static List<Image> imageList;
    public static Map<Image, String> captions;

    public static void populateData(File directory) throws MalformedURLException {
        imageList = new ArrayList<>();
        for (File file : Objects.requireNonNull(directory.listFiles())) {
            String path = file.getPath();
            String extension = path.substring(path.lastIndexOf(".") + 1);
            if (Arrays.stream(EXTENSIONS).anyMatch(extension::equalsIgnoreCase)) {
                Image image = new Image(file.toURI().toURL().toExternalForm());
                imageList.add(image);
                try { loadCaptions(directory.getPath()); }
                catch (FileNotFoundException e) {
                    captions = new LinkedHashMap<>();
                    captions.put(image, "");
                }
            }
        }
    }

    private static void loadCaptions(String directoryPath) throws FileNotFoundException {
        captions = new LinkedHashMap<>();
        File captionsFile = new File(directoryPath + "\\captions.txt");
        Scanner scanner = new Scanner(captionsFile);
        while (scanner.hasNextLine()) {
            String[] captionInfo = scanner.nextLine().split(": ");
            String imageName = captionInfo[0];
            String captionText = captionInfo[1];
            for (Image image: imageList) {
                if (image.getUrl().contains(imageName)) {
                    captions.put(image, captionText);
                    break;
                }
            }
        }
    }

    public static void saveAlbum(File newDirectory) {
        for (Image image: imageList) {
            String path = newDirectory.getPath() + "\\" + image.getUrl().substring(image.getUrl().lastIndexOf("/") + 1);
            try (InputStream in = URI.create(image.getUrl()).toURL().openStream()) {
                Files.copy(in, Path.of(path));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeCaptions(newDirectory.getPath());
    }

    private static void writeCaptions(String directoryPath) {
        StringBuilder output = new StringBuilder();
        for (Map.Entry<Image, String> entry: captions.entrySet()) {
            String imageName = entry.getKey().getUrl().substring(entry.getKey().getUrl().lastIndexOf("/") + 1);
            String caption = entry.getValue();
            output.append(String.format("%s: %s%n", imageName, caption));
        }
        try (OutputStream out = Files.newOutputStream(Path.of(directoryPath + "\\captions.txt"))) {
            out.write(output.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(JIMachine.class.getResource("java-image-loader.fxml"));
        Parent parent = fxmlLoader.load();
        JIMController controller = fxmlLoader.getController();
        controller.setStage(stage);
        Scene scene = new Scene(parent, 1000, 700);
        stage.setTitle("Java Image Machine");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    public static void main(String[] args) { launch(); }
}