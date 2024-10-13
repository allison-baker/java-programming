// Project Prolog
// Name: Al Baker
// CS3250 Section 601
// Project: Assignment 08
// Date: 09/30/2024
// Purpose: To test the Puzzle class.

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PuzzleTest {
    private static final int SIZE = 3;
    private static final Puzzle testPuzzle = new Puzzle(SIZE);

    @Test
    public void testGetSize() { assertEquals(SIZE, testPuzzle.getSize()); }

    @Test
    public void testGetChar() {
        char c = 'A';
        int row = 0;
        int col = 0;
        testPuzzle.addChar(c, row, col);
        assertEquals(c, testPuzzle.getCharAt(row, col));
    }
}
