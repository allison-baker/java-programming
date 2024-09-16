// Project Prolog
// Name: Al Baker
// CS3250 Section 601
// Project: Assignment 03
// Date: 09/14/2024
// Purpose: This program takes the weight and height from the user and outputs the BMI.

package com.almariebaker;

import java.util.Scanner;

public class ComputeAndInterpretBMI {
    public static void main(String[] args) {
        // Prepare to get user input from terminal
        Scanner input = new Scanner(System.in);

        // Prompt the user to enter weight in pounds
        System.out.print("Enter weight in pounds: ");
        double weight = input.nextDouble();

        // Prompt the user to enter height with one value each for feet and inches
        System.out.print("Enter height in two values - feet and inches: ");

        // Calculate the height in just inches
        double height = calculateHeight(input.nextDouble(), input.nextDouble());

        // Calculate the BMI with the weight and height
        double bmi = calculateBMI(weight, height);

        // Print the BMI and interpretation to terminal
        System.out.println("BMI is " + Math.round(bmi * 100.0) / 100.0);
        System.out.println(interpretBMI(bmi));
    }

    /* ------------ SPLIT THE MAIN METHOD INTO THREE HELPER METHODS FOR EASIER TESTING ------------ */

    // Takes two doubles for feet and inches and returns the height in inches
    public static double calculateHeight(double f, double i) {
        return (f * 12) + i;
    }

    // Takes two doubles for weight and height and returns the BMI
    public static double calculateBMI(double w, double h) {
        final double KILOGRAMS_PER_POUND = 0.45359237; // Constant
        final double METERS_PER_INCH = 0.0254; // Constant

        // Convert to metric units
        double weightInKilograms = w * KILOGRAMS_PER_POUND;
        double heightInMeters = h * METERS_PER_INCH;

        // Calculate and return BMI
        return weightInKilograms / (heightInMeters * heightInMeters);
    }

    // Takes a double for BMI and returns a string interpretation
    public static String interpretBMI(double bmi) {
        if (bmi < 18.5)
            return "Underweight";
        else if (bmi < 25)
            return "Normal";
        else if (bmi < 30)
            return "Overweight";
        else
            return "Obese";
    }
}
