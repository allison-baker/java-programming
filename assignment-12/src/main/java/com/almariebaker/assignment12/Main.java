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
    static String filePath;
    static int numWords;
    static List<String> lines;
    static Map<String, WordData> unsortedMap;

    public static List<String> readFile(String path) {
        List<String> fileLines = new ArrayList<>();

        // Create Scanner for reading from the file
        try (Scanner fileScanner = new Scanner(new File(path))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                // Add each line with content to the ArrayList
                if (!line.isBlank()) {
                    fileLines.add(line);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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

    public static void printAlpha(int userNum, Map<String, WordData> map) {
        System.out.printf("\nFirst %d words, alphabetically:\n", userNum);
        System.out.println("\"Word\"");
        System.out.println("\tPosition, Capitalized");

        // Create list to hold the first numWords unique words
        List<WordData> commonWords = new ArrayList<>();

        int count = 0;
        for (Map.Entry<String, WordData> entry : map.entrySet()) {
            // Add ot common words list
            if (count < userNum) {
                commonWords.add(entry.getValue());
                count++;
            } else break;
        }

        // Sort the list alphabetically
        commonWords.sort(Comparator.comparing(WordData::getWord));

        // Print all words and occurrences
        for (WordData word : commonWords) {
            System.out.printf("\"%s\"\n", word.getWord());
            for (Tuple occurrence : word.getOccurrences()) {
                System.out.printf("\t%d %b\n", occurrence.getIndex(), occurrence.isCapitalized());
            }
        }
    }

    public static void printByCount(int userNum, Map<String, WordData> map) {
        System.out.printf("\n%d words by count: \"Word\", Count\n", userNum);

        // Create a list of WordData objects from the map
        List<WordData> countList = new ArrayList<>();
        for (Map.Entry<String, WordData> entry : map.entrySet()) {
            countList.add(entry.getValue());
        }

        countList.sort((o1, o2) -> o2.getCount() - o1.getCount());

        int count = 0;
        for (WordData data : countList) {
            // Just print the number of words that the user requested
            if (count < userNum) {
                System.out.printf("\"%s\" %d\n", data.getWord(), data.getCount());
                count++;
            } else break;
        }
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("word-search.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}