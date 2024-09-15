// Project Prolog
// Name: Al Baker
// CS3250 Section 001
// Project: Assignment 02
// Date: 09/11/2024
// Purpose: Write a simple program and practice using JUnit5 to test it.

package com.almariebaker;

import java.util.Scanner;

public class LargestElementFinder {
    public static void main(String[] args) {
        // Prepare to get user input from terminal
        Scanner scanner = new Scanner(System.in);

        // Ask for the number of rows and columns - makes a 2D array
        System.out.print("Enter the number of rows and columns of the array: ");
        int rows = scanner.nextInt();
        int columns = scanner.nextInt();

        // Create array
        double[][] array = new double[rows][columns];

        // Ask user for array elements
        System.out.println("Enter the array: ");

        // Loop through input and populate array
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                array[i][j] = scanner.nextDouble();
            }
        }

        // Find location of largest element using locateLargest method
        int[] location = locateLargest(array);

        // Print result for the user
        System.out.printf("The location of the largest element is at (%d, %d)\n", location[0], location[1]);
    }

    // locateLargest method, takes a 2D array of doubles and returns location of largest element
    public static int[] locateLargest(double[][] a) {
        int[] location = {0, 0};

        // Initialize the maximum to the first element in 2D array
        double max = a[0][0];

        // Loop through the 2D array and compare to the max, reassign max if larger
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                if (a[i][j] > max) {
                    max = a[i][j];

                    // Update location of largest element
                    location[0] = i;
                    location[1] = j;
                }
            }
        }

        // Return location of largest element
        return location;
    }
}
