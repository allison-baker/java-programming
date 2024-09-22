import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Get file path from user
        String filePath = args[0];

        // Get number of words from user
        int numWords = Integer.parseInt(args[1]);

        // List of lines
        List<String> lines = new ArrayList<>();

        // Create a map to store the words and their counts
        Map<String, Index> wordMap = new HashMap<>();

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

        // Initialize a variable for the current index
        int currIndex = 0;

        // Iterate through ArrayList of lines
        for (String line : lines) {
            // Create an array of words from the current line
            String[] words = line.replaceAll("\\p{P}", "").split("\\s+");

            // Iterate through the array of words
            for (String word : words) {
                // The key in the map is always lower case. Check if the map has that word
                if (wordMap.containsKey(word.toLowerCase())) {
                    // Add a new occurrence with the index and capitalization
                    wordMap.get(word.toLowerCase()).addOccurrence(currIndex, Character.isUpperCase(word.charAt(0)));
                } else {
                    // Add the word to the map
                    wordMap.put(word.toLowerCase(), new Index(currIndex, Character.isUpperCase(word.charAt(0))));
                }
                // Update the current index
                currIndex++;
            }
        }

        // Output the words and their counts
    }
}
