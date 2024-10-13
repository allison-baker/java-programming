// Project Prolog
// Name: Al Baker
// CS3250 Section 601
// Project: Assignment 08
// Date: 09/30/2024
// Purpose: Read in a text file and create a word search game.

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Search {
    static Puzzle puzzle;
    static List<String> wordsToFind = new ArrayList<>();

    // Must keep track of direction, or puzzle could incorrectly find words not in straight lines
    static String[] directions = {"NW", "N", "NE", "W", "Current", "E", "SW", "S", "SE"};

    public static void readFile(String name) {
        // Create new scanner for file using file name from user
        try (Scanner stdin = new Scanner(new File(name))) {
            // Read first line to get size of puzzle
            String line = stdin.nextLine();
            int size = line.length();

            // Initialize puzzle with size
            puzzle = new Puzzle(size);

            // Loop through using size to populate puzzle
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    puzzle.addChar(line.charAt(j), i, j);
                }
                line = stdin.nextLine();
            }

            // The rest of the file has the words to find, loop through and add to list
            while (stdin.hasNext()) {
                String word = stdin.nextLine();
                wordsToFind.add(word);
            }
        } catch(FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

    public static void playGame() {
        // Loop through words to find and search for them
        for (String word : wordsToFind) {
            // Create map to hold start and end locations
            Map<String, int[]> location = new HashMap<>();

            // Loop through puzzle to find start of words
            for (int i = 0; i < puzzle.getSize(); i++) {
                for (int j = 0; j < puzzle.getSize(); j++) {
                    // If first char of current word matches current location, search for word
                    if (word.charAt(0) == puzzle.getCharAt(i, j)) {
                        int [] end = searchWord(word.substring(1), i, j, "");

                        // End will be {-1, -1} if complete word isn't found
                        if (end[0] != -1) {
                            location.put("start", new int[]{i, j});
                            location.put("end", end);
                        }
                    }
                }
            }

            // If location is empty, word wasn't found
            if (location.isEmpty()) {
                System.out.printf("%s not found\n", word);
            } else {
                System.out.printf("%s found at start: %d, %d end: %d, %d\n", word, location.get("start")[0], location.get("start")[1], location.get("end")[0], location.get("end")[1]);
            }
        }
    }

    public static int[] searchWord(String word, int row, int col, String direction) {
        // Check if you've reached the end of the word, return current location
        if (word.isBlank()) { return new int[]{row, col}; }

        // Initialize index for directions array
        int dir = 0;

        // Loop through 3x3 grid around current location
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                // Check if out of puzzle
                if (i < 0 || i >= puzzle.getSize() || j < 0 || j >= puzzle.getSize()) {
                    dir++;
                    continue;
                }

                // Check if current location
                if (directions[dir].equals("Current")) {
                    dir++;
                    continue;
                }

                // Check for match, call recursively if true. Pass direction to make sure word is found in straight line
                if (puzzle.getCharAt(i, j) == word.charAt(0)) {
                    /*
                        If it's the start of the word and there's no current direction, there's a possibility the program
                        could find the second letter in one direction but not the rest of the word and abort the search
                        too early. This will check all 8 directions if the second letter is found but not the rest.
                    */
                    if (direction.isBlank()) {
                        int[] potentialMatch = searchWord(word.substring(1), i, j, directions[dir]);
                        if (potentialMatch[0] == -1) {
                            dir++;
                            continue;
                        } else {
                            return potentialMatch;
                        }
                    } else if (direction.equals(directions[dir])) {
                        return searchWord(word.substring(1), i, j, directions[dir]);
                    }
                }

                dir++;
            }
        }

        // Will only return if no match is found in all 8 directions
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        readFile(args[0]);
        playGame();
    }
}
