// Project Prolog
// Name: Al Baker
// CS3250 Section 601
// Project: Assignment 12
// Date: 11/04/2024
// Purpose: Controller for the GUI for the word search from Assignment 05.

package com.almariebaker.assignment12;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static com.almariebaker.assignment12.Main.*;

public class MainController {
    @FXML private TextField fileName;
    @FXML private Text message;
    @FXML private Button scrapeBtn;
    @FXML private Text message2;
    @FXML private TextField numWords;
    @FXML private VBox resultsBox;
    @FXML private TextField search;
    @FXML private Label errorMessage;
    @FXML private Text results;
    @FXML private Label type;
    String path;
    int userNum;
    String searchTerm;

    @FXML
    protected void getFile() {
        path = fileName.getText();
        try {
            lines = readFile(path);
            message.setFill(javafx.scene.paint.Color.GREEN);
            message.setText("File loaded successfully.");
            scrapeBtn.setDisable(false);
        } catch (FileNotFoundException e) {
            message.setFill(javafx.scene.paint.Color.RED);
            message.setText("File not found. Please try again.");
        }
    }

    @FXML
    protected void scrapeFile() {
        if (!Pattern.matches("^[0-9]+$", numWords.getText())) {
            message2.setFill(javafx.scene.paint.Color.RED);
            message2.setText("Please enter a number.");
            return;
        }

        userNum = Integer.parseInt(numWords.getText());

        if (userNum <= 0) {
            message2.setFill(javafx.scene.paint.Color.RED);
            message2.setText("Please enter a positive number.");
            return;
        }

        unsortedMap = populateMap(lines);
        message2.setFill(javafx.scene.paint.Color.GREEN);
        message2.setText("File scraped successfully.");
        resultsBox.setVisible(true);
    }

    @FXML
    protected void printAlpha() {
        String displayNum;
        if (unsortedMap.size() < userNum) {
            errorMessage.setText("Number entered is greater than number of unique words in file. Displaying all unique words.");
            displayNum = "All";
        } else {
            displayNum = String.valueOf(userNum);
        }

        type.setText(String.format("%s Words Alphabetically:\n\"Word\"\n\tIndex, Capitalization", displayNum));
        List<WordData> uniqueWords = new ArrayList<>();

        int count = 0;
        for (Map.Entry<String, WordData> entry : unsortedMap.entrySet()) {
            if (count < userNum) {
                uniqueWords.add(entry.getValue());
                count++;
            } else break;
        }

        uniqueWords.sort(Comparator.comparing(WordData::getWord));

        String output = "";
        for (WordData word : uniqueWords) {
            output = output.concat(String.format("\"%s\"\n", word.getWord()));
            for (Tuple occurrence : word.getOccurrences()) {
                output = output.concat(String.format("\t%d %b\n", occurrence.getIndex(), occurrence.isCapitalized()));
            }
        }

        results.setText(output);
    }

    @FXML
    protected void printByCount() {
        String displayNum;
        if (unsortedMap.size() < userNum) {
            errorMessage.setText("Number entered is greater than number of unique words in file. Displaying all unique words.");
            displayNum = "All";
        } else {
            displayNum = String.valueOf(userNum);
        }

        type.setText(String.format("%s Words by Count:\n\"Word\", Count", displayNum));

        List<WordData> countList = new ArrayList<>();
        for (Map.Entry<String, WordData> entry : unsortedMap.entrySet()) {
            countList.add(entry.getValue());
        }

        countList.sort((o1, o2) -> o2.getCount() - o1.getCount());

        int count = 0;
        String output = "";
        for (WordData data : countList) {
            if (count < userNum) {
                output = output.concat(String.format("\"%s\" %d\n", data.getWord(), data.getCount()));
                count++;
            } else break;
        }

        results.setText(output);
    }

    @FXML
    protected void searchWord() {
        searchTerm = search.getText().toLowerCase();
        if (unsortedMap.get(searchTerm) != null) {
            type.setText(String.format("Search Results for \"%s\":\n\"Word\", Count", searchTerm));
            results.setText(String.format("\"%s\" %d", searchTerm, unsortedMap.get(searchTerm).getCount()));
        } else {
            type.setText("");
            results.setText("");
            errorMessage.setText("Word not found in file. Please try again.");
        }
    }

    @FXML
    protected void clear() {
        fileName.clear();
        message.setText("");
        scrapeBtn.setDisable(true);
        message2.setText("");
        numWords.clear();
        type.setText("");
        results.setText("");
        lines = null;
        unsortedMap = null;
        errorMessage.setText("");
        resultsBox.setVisible(false);
    }

    @FXML protected void exit() { System.exit(0); }
}