// Project Prolog
// Name: Al Baker
// CS3250 Section 601
// Project: Assignment 06
// Date: 09/26/2024
// Purpose: Expanding upon the original Adventure game to provide more functionality.

import java.util.*;

public class Adventure {
    String currCommand = null;
    GameChar character;
    Map map;

    public Adventure(int row, int col) { character = new GameChar(row, col); }

    public void setMap(Map map) { this.map = map; }

    void processQuit() {
        System.out.printf("Your current location is: {%1$d, %2$d}%n", character.getRow(), character.getCol());
        map.printTerrain(character.getRow(), character.getCol());
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
                    character.processGo(commands[1].charAt(0), map);
                } else {
                    System.out.println("Must include a direction with 'go' command.");
                }
                character.printLocation(map);
                break;

            // Inventory command prints inventory
            case 'i':
            case 'I':
                currCommand = "inventory";
                character.printInventory();
                character.printLocation(map);
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
                character.printLocation(map);
                break;
        }
    }

    public static void main(String[] args) {
        Adventure adventure = new Adventure(0, 0);
        Map map = new Map(args[0]);
        adventure.setMap(map);

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
