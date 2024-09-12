// Project Prolog
// Name: Al Baker
// CS3250 Section 001
// Project: Assignment 02
// Date: 09/11/2024
// Purpose: Write test functions for the LargestElementFinder class.

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import com.almariebaker.LargestElementFinder;

public class LargestElementFinderTest {
    @Test
    void testLocateLargest() {
        double[][] array = {
            {23.5, 35, 2, 10},
            {4.5, 3, 45, 3.5},
            {35, 44, 5.5, 9.6}
        };
        int[] expected = {1, 2};
        assertArrayEquals(expected, LargestElementFinder.locateLargest(array));
    }
}
