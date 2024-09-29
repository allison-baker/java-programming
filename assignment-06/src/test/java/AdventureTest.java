// Project Prolog
// Name: Al Baker
// CS3250 Section 601
// Project: Assignment 06
// Date: 09/26/2024
// Purpose: This program tests the updated Adventure class with additional functionality.

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AdventureTest {
    private static final Adventure adventureTest = new Adventure(0, 0);

    // Set up map with test file before other tests
    @BeforeAll
    static void setMap() {
        Map map = new Map("src/test/resources/map.txt");
        adventureTest.setMap(map);
    }

    // Test print inventory method
    @Test
    public void testPrintInventory() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        // Call print inventory method
        adventureTest.character.printInventory();

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
                    assertEquals(0, adventureTest.character.currRow);
                },
                () -> {
                    for (int i = 0; i < adventureTest.map.getMaxRows(); i++) {
                        adventureTest.startAdventure("g s");
                    }
                    assertEquals(9, adventureTest.character.currRow);
                },
                () -> {
                    adventureTest.startAdventure("g w");
                    assertEquals(0, adventureTest.character.currCol);
                },
                () -> {
                    for (int i = 0; i < adventureTest.map.getMaxCols(); i++) {
                        adventureTest.startAdventure("g e");
                    }
                    assertEquals(11, adventureTest.character.currCol);
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

    // Test map files that are the wrong dimensions
    @Test
    public void testErrorMap() {
        Exception e = assertThrows(RuntimeException.class, () -> new Map("src/test/resources/errorMap.txt"));
        assertEquals("Map file does not match dimensions.", e.getMessage());
    }

    // Test that the map class prints the terrain correctly
    @Test
    public void testPrintTerrain() {
        // Test top left corner, middle of the map, bottom right corner
        assertAll("printing terrain",
            () -> {
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                PrintStream originalOut = System.out;
                System.setOut(new PrintStream(outputStream));
                adventureTest.map.printTerrain(0, 0);
                String expected = "XXX" + System.lineSeparator() + "X~~" + System.lineSeparator() + "X~~";
                assertEquals(expected, outputStream.toString().trim());
                System.setOut(originalOut);
            },
            () -> {
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                PrintStream originalOut = System.out;
                System.setOut(new PrintStream(outputStream));
                adventureTest.map.printTerrain(5, 6);
                String expected = "MMM" + System.lineSeparator() + "fMM" + System.lineSeparator() + "ffM";
                assertEquals(expected, outputStream.toString().trim());
                System.setOut(originalOut);
            },
            () -> {
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                PrintStream originalOut = System.out;
                System.setOut(new PrintStream(outputStream));
                adventureTest.map.printTerrain(9, 11);
                String expected = "f.X" + System.lineSeparator() + "ffX" + System.lineSeparator() + "XXX";
                assertEquals(expected, outputStream.toString().trim());
                System.setOut(originalOut);
            }
        );
    }
}
