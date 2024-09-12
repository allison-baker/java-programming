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

    @Test
    void testLocateLargestFirst() {
        double[][] array = {
            {100, 35, 6, 95},
            {40.5, 72, 32.4, 1.5},
            {60, 4.7, 9.5, 11}
        };
        int[] expected = {0, 0};
        assertArrayEquals(expected, LargestElementFinder.locateLargest(array));
    }

    @Test
    void testLocateLargestLast() {
        double[][] array = {
                {2, 4.5, 15, 60},
                {13.6, 5, 77, 23.5},
                {30, 53, 8.3, 80}
        };
        int[] expected = {2, 3};
        assertArrayEquals(expected, LargestElementFinder.locateLargest(array));
    }

    @Test
    void testLocateLargestSame() {
        double[][] array = {
                {5, 5, 5, 5},
                {5, 5, 5, 5},
                {5, 5, 5, 5}
        };
        int[] expected = {0, 0};
        assertArrayEquals(expected, LargestElementFinder.locateLargest(array));
    }
}
