// Project Prolog
// Name: Al Baker
// CS3250 Section 601
// Project: Assignment 06
// Date: 09/26/2024
// Purpose: GameChar class that handles the go and inventory commands and current location of user.

import java.util.*;

public class GameChar {
    int currRow;
    int currCol;
    static List<String> inventory = new ArrayList<>() {
        {
            add("brass lantern");
            add("rope");
            add("rations");
            add("staff");
        }
    };

    public GameChar(int row, int col) {
        currRow = row;
        currCol = col;
    }

    public int getRow() { return currRow; }

    public int getCol() { return currCol; }

    public void printInventory() {
        System.out.println("Your current inventory:");
        for (String item : inventory) {
            System.out.println(item);
        }
    }

    void printLocation(Map map) {
        System.out.printf("Your current location is: {%1$d, %2$d}%n", currRow, currCol);
        map.printTerrain(currRow, currCol);
        System.out.print("Enter a command: ");
    }

    void processGo(char currDirection, Map map) {
        switch(currDirection) {
            case 'n':
            case 'N':
                if (currRow > map.getMinRows()) {
                    currRow--;
                } else {
                    System.out.println("You cannot go north from here. Try another direction.");
                }
                break;
            case 'e':
            case 'E':
                if (currCol < map.getMaxCols() - 1) {
                    currCol++;
                } else {
                    System.out.println("You cannot go east from here. Try another direction.");
                }
                break;
            case 's':
            case 'S':
                if (currRow < map.getMaxRows() - 1) {
                    currRow++;
                } else {
                    System.out.println("You cannot go south from here. Try another direction.");
                }
                break;
            case 'w':
            case 'W':
                if (currCol > map.getMinCols()) {
                    currCol--;
                } else {
                    System.out.println("You cannot go west from here. Try another direction.");
                }
                break;
            default:
                System.out.println("Invalid direction. Valid directions are: 'north', 'east', 'south', or 'west'.");
                break;
        }
    }
}
