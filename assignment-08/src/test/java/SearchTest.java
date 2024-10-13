// Project Prolog
// Name: Al Baker
// CS3250 Section 601
// Project: Assignment 08
// Date: 09/30/2024
// Purpose: Test the word search game.

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

public class SearchTest {
    private static final int SIZE = 10;
    private static final String FILE = "src/test/resources/wordSearchTest.txt";
    private static List<String> testWords;
    private static Puzzle testPuzzle;

    @BeforeAll
    static void setUp() {
        String[] lines = new String[]{
            "HGAMONIHRA",
            "AOMOKAWONS",
            "NFROLBOBDN",
            "ARFSIHCAGE",
            "LNIEEWONOK",
            "GOLFUNDTHC",
            "KOCATAOHBI",
            "AMRERCGANH",
            "SLGFAMALLC",
            "ALLIGATORX"
        };

        testPuzzle = new Puzzle(SIZE);
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                testPuzzle.addChar(lines[i].charAt(j), i, j);
            }
        }

        testWords = new ArrayList<>(Arrays.asList(
                "HORSE",
                "COW",
                "RHINO",
                "JABBERWOCKY",
                "CAT",
                "DOG",
                "ALLIGATOR",
                "CHICKEN",
                "FROG",
                "BANTHA",
                "MOOSE",
                "LLAMA"
        ));

        Search.readFile(FILE);
    }

    // Test reading in file and creating puzzle and word list
    @Test
    public void testReadFile() {
        assertAll("Read file",
            () -> assertEquals(testWords, Search.wordsToFind),
            () -> {
                for (int i = 0; i < SIZE; i++) {
                    for (int j = 0; j < SIZE; j++) {
                        assertEquals(testPuzzle.getCharAt(i, j), Search.puzzle.getCharAt(i, j));
                    }
                }
            }
        );
    }

    // Test individual word search
    @Test
    public void testWord() {
        assertAll("testing individual words",
            () -> {
                int[] start = {0, 0};
                int[] end = Search.searchWord("ORSE", start[0], start[1], "");
                assertAll("HORSE",
                    () -> assertEquals(4, end[0]),
                    () -> assertEquals(4, end[1])
                );
            },
            () -> {
                // Test CAT with incorrect start, make sure it returns -1
                int[] start = {7, 5};
                int[] end = Search.searchWord("CAT", start[0], start[1], "");
                assertEquals(-1, end[0]);
            },
            () -> {
                // Test word with multiple potential second letter matches around it
                int[] start = {9, 0};
                int[] end = Search.searchWord("LLIGATOR", start[0], start[1], "");
                assertAll("two potential second letters",
                    () -> assertEquals(9, end[0]),
                    () -> assertEquals(8, end[1])
                );
            }
        );
    }

    // TODO: Test whole search game
    @Test
    public void testGame() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        Search.playGame();

        String output = outputStream.toString();
        String expected = """
                HORSE found at start: 0, 0 end: 4, 4
                COW found at start: 3, 6 end: 1, 6
                RHINO found at start: 0, 8 end: 0, 4
                JABBERWOCKY not found
                CAT found at start: 6, 2 end: 6, 4
                DOG found at start: 5, 6 end: 7, 6
                ALLIGATOR found at start: 9, 0 end: 9, 8
                CHICKEN found at start: 8, 9 end: 2, 9
                FROG found at start: 8, 3 end: 5, 0
                BANTHA found at start: 2, 7 end: 7, 7
                MOOSE found at start: 0, 3 end: 4, 3
                LLAMA found at start: 8, 8 end: 8, 4""";
        assertEquals(expected, output.trim());

        System.setOut(originalOut);
    }
}
