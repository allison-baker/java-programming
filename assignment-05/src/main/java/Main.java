import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Get file path and number of words from the user
        String filePath = args[0];
        int numWords = Integer.parseInt(args[1]);

        // List of lines
        List<String> lines = new ArrayList<>();

        // Create Scanner for reading from the file
        try (Scanner fileScanner = new Scanner(new File(filePath))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                // Add each line with content to the ArrayList
                if (!line.isBlank()) {
                    lines.add(line);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Initialize a variable for the current index and a new list for the words
        int currIndex = 0;
        WordLinkedList wordList = new WordLinkedList();

        // Iterate through ArrayList of lines
        for (String line : lines) {
            // Create an array of words from the current line, remove digits and split on white space
            String[] words = line.replaceAll("\\d", "").split("\\s+");

            // Iterate through the array of words
            for (String word : words) {
                // Remove any leading or trailing punctuation
                word = word.replaceAll("^\\p{P}+", "").replaceAll("\\p{P}+$", "");

                // Check if the word is empty
                if (!word.isBlank()) {
                    // Check if the word is capitalized
                    boolean capitalized = Character.isUpperCase(word.charAt(0));

                    // TODO: Check if word already exists in list and if so add occurrence instead of making new object

                    // Create a new WordData object
                    WordData data = new WordData(word, currIndex, capitalized);

                    // Add the word to the WordLinkedList
                    wordList.addBack(data);

                    // Increment the current index
                    currIndex++;
                }
            }
        }
        // TODO: Print alphabetized list for the number of words user entered

        // TODO: Print words by count for the number of words user entered
    }
}
