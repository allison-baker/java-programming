// Project Prolog
// Name: Al Baker
// CS3250 Section 601
// Project: Assignment 12
// Date: 11/04/2024
// Purpose: Create a GUI for the word search from Assignment 05.

package com.almariebaker.assignment12;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Main extends Application {
    static List<String> lines;
    static Map<String, WordData> unsortedMap;

    public static List<String> readFile(String path) throws FileNotFoundException {
        List<String> fileLines = new ArrayList<>();

        Scanner fileScanner = new Scanner(new File(path));
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            if (!line.isBlank()) {
                fileLines.add(line);
            }
        }

        return fileLines;
    }

    public static Map<String, WordData> populateMap(List<String> fileLines) {
        Map<String, WordData> map = new LinkedHashMap<>();
        int currIndex = 0;

        for (String fileLine : fileLines) {
            // Create an array of words in the current line, remove digits and split on white space
            String[] words = fileLine.replaceAll("\\d", "").split("\\s+");

            for (String word : words) {
                // Remove any leading or trailing punctuation on word
                word = word.replaceAll("^\\p{P}+", "").replaceAll("\\p{P}+$", "");

                if (!word.isBlank()) {
                    boolean capitalized = Character.isUpperCase(word.charAt(0));

                    // Check if word already exists in list, add occurrence or create new WordData object and add to map
                    if (map.get(word.toLowerCase()) != null) {
                        map.get(word.toLowerCase()).addOccurrence(currIndex, capitalized);
                    } else {
                        WordData data = new WordData(word.toLowerCase(), currIndex, capitalized);
                        map.put(word.toLowerCase(), data);
                    }

                    currIndex++;
                }
            }
        }

        return map;
    }

    public static Boolean searchFile(String searchTerm, Map<String, WordData> map) { return map.get(searchTerm) != null; }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("word-search.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        stage.setTitle("File Scraper");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}