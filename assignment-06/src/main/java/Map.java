// Project Prolog
// Name: Al Baker
// CS3250 Section 601
// Project: Assignment 06
// Date: 09/26/2024
// Purpose: Map class that handles building the map from input file and printing terrain.

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Map {
    int maxRows;
    int maxCols;
    int MIN_ROWS = 0;
    int MIN_COLS = 0;
    char[][] map;

    public Map(String file) {
        // New scanner to read map file
        try (Scanner mapScanner = new Scanner(new File(file))) {
            String line = mapScanner.nextLine();

            // Get first two numbers for dimensions
            String[] dimensions = line.split(" ");

            // Set class attributes
            maxRows = Integer.parseInt(dimensions[0]);
            maxCols = Integer.parseInt(dimensions[1]);
            map = new char[maxRows][maxCols];

            // Populate map array with characters
            for (int i = 0; i < maxRows; i++) {
                if (!mapScanner.hasNextLine()) { throw new RuntimeException("Map file does not match dimensions."); }
                String row = mapScanner.nextLine();
                for (int j = 0; j < maxCols; j++) {
                    if (row.length() != maxCols) { throw new RuntimeException("Map file does not match dimensions."); }
                    map[i][j] = row.charAt(j);
                }
            }
        } catch(FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public int getMaxRows() { return maxRows; }

    public int getMaxCols() { return maxCols; }

    public int getMinRows() { return MIN_ROWS; }

    public int getMinCols() { return MIN_COLS; }

    public void printTerrain(int row, int col) {
        // Loop through 3x3 grid with user in center
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                // Check if user is on the edge of the map, if so print X to show boundary
                if (i < MIN_ROWS || i >= maxRows || j < MIN_COLS || j >= maxCols) {
                    System.out.print("X");
                } else {
                    // Print column character
                    System.out.print(map[i][j]);
                }
            }
            // Move to new row
            System.out.println();
        }
    }
}
