// Project Prolog
// Name: Al Baker
// CS3250 Section 601
// Project: Assignment 06
// Date: 09/26/2024
// Purpose: Expanding upon the original Adventure game to provide more functionality.

import java.util.*;

public class Adventure {
    int currRow;
    int currCol;
    char currDirection = Character.MIN_VALUE;
    String currCommand = null;
    static int MAX_ROWS = 5;
    static int MAX_COLS = 5;
    static int MIN_ROWS = 0;
    static int MIN_COLS = 0;
    static String[] INVENTORY = new String[4];

    public Adventure(int row, int col, String[] inventory) {
        currRow = row;
        currCol = col;
        INVENTORY = inventory;
    }

    void printLocation() {
        System.out.printf("Your current location is: {%1$d, %2$d}%n", currRow, currCol);
        System.out.print("Enter a command: ");
    }

    void processGo() {
        // Switch case for processing direction
        switch(currDirection) {
            case 'n':
            case 'N':
                if (currRow > MIN_ROWS) {
                    currRow--;
                } else {
                    System.out.println("You cannot go north from here. Try another direction.");
                }
                break;
            case 'e':
            case 'E':
                if (currCol < MAX_COLS - 1) {
                    currCol++;
                } else {
                    System.out.println("You cannot go east from here. Try another direction.");
                }
                break;
            case 's':
            case 'S':
                if (currRow < MAX_ROWS - 1) {
                    currRow++;
                } else {
                    System.out.println("You cannot go south from here. Try another direction.");
                }
                break;
            case 'w':
            case 'W':
                if (currCol > MIN_COLS) {
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

    void printInventory() {
        System.out.println("Your current inventory:");
        for (String item : INVENTORY) {
            System.out.println(item);
        }
    }

    void processQuit() {
        System.out.printf("Your current location is: {%1$d, %2$d}%n", currRow, currCol);
        throw new RuntimeException("Farewell!");
    }

    public void startAdventure(String command) {
        // While there is input, process commands
        // Commands can be abbreviated, commands are not case-sensitive, and location is printed after each command
        switch(command.charAt(0)) {
            // All four go parameters work, location is correct, location stays in bounds, error handling
            case 'g':
            case 'G':
                currCommand = "go";
                // Get second word from line for direction
                String[] commands = command.split(" ");
                if (commands.length > 1) {
                    currDirection = commands[1].charAt(0);
                    processGo();
                } else {
                    System.out.println("Must include a direction with 'go' command.");
                }
                printLocation();
                break;

            // Inventory command prints inventory
            case 'i':
            case 'I':
                currCommand = "inventory";
                printInventory();
                printLocation();
                break;

            // Quit command exits the program
            case 'q':
            case 'Q':
                currCommand = "quit";
                processQuit();
                break;

            // Prints error messages for invalid commands
            default:
                System.out.println("Invalid command. Valid commands are: 'go <direction>', 'inventory', or 'quit'.");
                printLocation();
                break;
        }
    }

    public static void main(String[] args) {
        Adventure adventure = new Adventure(0, 0, new String[]{"brass lantern", "rope", "rations", "staff"});

        // Prepare to get user input from terminal
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a command: ");

        while(input.hasNext()) {
            try {
                String command = input.nextLine();
                adventure.startAdventure(command);
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
                break;
            }
        }
    }
}
