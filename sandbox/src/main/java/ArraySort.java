/**
 * CS3250
 * Array sort
 */

import java.util.Arrays;

public class ArraySort {

        public static void main(String args[])
        {

            int[] arr = { 5, -2, 23, 7, 87, -42, 509 };
            System.out.println("The original array is: ");
            for (int num : arr) {
                System.out.print(num + " ");
            }
            // Sort an array using built in array.sort()
            Arrays.sort(arr);
            System.out.println("\nThe sorted array is: ");
            for (int num : arr) {
                System.out.print(num + " ");
            }
        }
    }

