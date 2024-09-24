import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    static String filePath;
    static int numWords;
    static List<String> lines = new ArrayList<>();
    static Map<String, WordData> unsortedMap = new HashMap<>();

    static void readFile() {
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
    }

    static void populateMap() {
        int currIndex = 0;

        for (String line : lines) {
            // Create an array of words in the current line, remove digits and split on white space
            String[] words = line.replaceAll("\\d", "").split("\\s+");

            for (String word : words) {
                // Remove any leading or trailing punctuation on word
                word = word.replaceAll("^\\p{P}+", "").replaceAll("\\p{P}+$", "");

                if (!word.isBlank()) {
                    boolean capitalized = Character.isUpperCase(word.charAt(0));

                    // Check if word already exists in list, add occurrence or create new WordData object and add to map
                    if (unsortedMap.get(word.toLowerCase()) != null) {
                        unsortedMap.get(word.toLowerCase()).addOccurrence(currIndex, capitalized);
                    } else {
                        WordData data = new WordData(word.toLowerCase(), currIndex, capitalized);
                        unsortedMap.put(word.toLowerCase(), data);
                    }

                    currIndex++;
                }
            }
        }
    }

    static void printAlpha() {
        System.out.printf("Alphabetically: %d words\n", numWords);
        // Create tree map from original map to sort alphabetically
        Map<String, WordData> alphaMap = new TreeMap<>(unsortedMap);

        int count = 0;
        for (Map.Entry<String, WordData> entry : alphaMap.entrySet()) {
            // Just print the number of words the user requested
            if (count < numWords) {
                System.out.println(entry.getKey() + " " + entry.getValue().getCount());
                count++;
            } else break;
        }
        System.out.println();
    }

    static void printByCount() {
        System.out.printf("By count: %d words\n", numWords);

        // TODO: Create a list of WordData objects from the map

        System.out.println();
    }

    public static void main(String[] args) {
        // Get file path and number of words from the user
        filePath = args[0];
        numWords = Integer.parseInt(args[1]);

        // Read lines from the file
        readFile();

        // Populate the map with the words and their data
        populateMap();

        // Print number of words user entered in alphabetical order
        printAlpha();

        // Print number of words user entered in order of count
        printByCount();
    }
}
