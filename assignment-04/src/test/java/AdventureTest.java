// Project Prolog
// Name: Al Baker
// CS3250 Section 601
// Project: Assignment 04
// Date: 09/19/2024
// Purpose: This program tests the Adventure class.

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AdventureTest {
    private final Adventure adventureTest = new Adventure(0, 0, new String[] {"brass lantern", "rope", "rations", "staff"});

    // Test print inventory method
    @Test
    public void testPrintInventory() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        // Call print inventory method
        adventureTest.printInventory();

        // Check output
        String output = outputStream.toString();
        String expected = String.format("Your current inventory:%1$sbrass lantern%1$srope%1$srations%1$sstaff", System.lineSeparator());
        assertEquals(expected, output.trim());

        System.setOut(originalOut);
    }

    // Test abbreviated commands
    @Test
    public void testAbbreviations() {
        assertAll("abbreviations",
            () -> {
                adventureTest.startAdventure("g n ");
                assertEquals("go", adventureTest.currCommand);
            },
            () -> {
                adventureTest.startAdventure("i");
                assertEquals("inventory", adventureTest.currCommand);
            },
            () -> {
                Exception e = assertThrows(RuntimeException.class, () -> adventureTest.startAdventure("q"));
                assertEquals("quit", adventureTest.currCommand);
            }
        );
    }

    // Test case-insensitive commands
    @Test
    public void testCases() {
        assertAll("uppercase",
                () -> {
                    adventureTest.startAdventure("G N");
                    assertEquals("go", adventureTest.currCommand);
                },
                () -> {
                    adventureTest.startAdventure("I");
                    assertEquals("inventory", adventureTest.currCommand);
                },
                () -> {
                    Exception e = assertThrows(RuntimeException.class, () -> adventureTest.startAdventure("Q"));
                    assertEquals("quit", adventureTest.currCommand);
                }
        );
    }

    // Test boundaries
    @Test
    public void testBoundaries() {
        assertAll("boundaries",
            () -> {
                adventureTest.startAdventure("g n");
                assertEquals(0, adventureTest.currCol);
            },
            () -> {
                for (int i =0; i<5; i++) {
                    adventureTest.startAdventure("g s");
                }
                assertEquals(4, adventureTest.currCol);
            },
            () -> {
                adventureTest.startAdventure("g w");
                assertEquals(0, adventureTest.currRow);
            },
            () -> {
                for (int i =0; i<5; i++) {
                    adventureTest.startAdventure("g e");
                }
                assertEquals(4, adventureTest.currRow);
            });
    }

    // Test invalid commands
    @Test
    public void testInvalidCommands() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        adventureTest.startAdventure("x");
        assertEquals("Invalid command. Valid commands are: 'go <direction>', 'inventory', or 'quit'.", outputStream.toString().split(System.lineSeparator())[0]);

        System.setOut(originalOut);
    }

    // Test go command with no direction
    @Test
    public void testNoDirection() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        adventureTest.startAdventure("g");
        assertEquals("Must include a direction with 'go' command.", outputStream.toString().split(System.lineSeparator())[0]);

        System.setOut(originalOut);
    }
}
