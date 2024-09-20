// Project Prolog
// Name: Al Baker
// CS3250 Section 601
// Project: Assignment 04
// Date: 09/19/2024
// Purpose: A simple game that allows the user to travel in different directions and carry an inventory.

/* ---------- COMMANDS ---------- */
// go <direction> - options are north, south, east, or west
// inventory - list the items in the inventory
// brass lantern, rope, rations, staff
// quit - exit the program

/* ---------- SPECIFICATIONS ---------- */
// Recognize commands by first letter, case-insensitive
// Keep track of player location and print after each valid command
// Location is a pair of coordinates for row and column of the map
// Player starts at {0, 0} in top left corner of the map
// Playable area is 5 rows and 5 columns, starting at 0
// Cannot be negative
// Cannot be greater than 4
// Print an error message if the player tries to move out of bounds
// Must have unit tests that cover all functionality

/* ---------- TIPS ---------- */
// Get input with java.util.Scanner
// Break input line into words with String.split()
// Chapters 2, 3, 6, and 9 have information on compiling/running programs, Scanner class,
// working with strings and arrays, and creating/using classes/objects

import java.util.*;

public class Adventure {
    public static void main(String[] args) {
        // TODO: Inventory command prints inventory
        // TODO: commands can be abbreviated
        // TODO: Commands are not case sensitive
        // TODO: Location is printed after each command
        // TODO: Location is correct
        // TODO: Location is not out of bounds, and the program prints a message if a go command would take the player out of bounds
        // TODO: All four go parameters work
        // TODO: Prints error messages for invalid commands and parameters
        // TODO: Quit command exits the program
    }
}
