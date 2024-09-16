// Project Prolog
// Name: Al Baker
// CS 3250 Section 601
// Project: Assignment 03
// Date: 09/14/2024
// Purpose: This program takes the weight and height from the user and outputs the BMI.

package com.almariebaker;

import java.util.Scanner;

public class ComputeAndInterpretBMI {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Prompt the user to enter weight in pounds
        System.out.print("Enter weight in pounds: ");
        double weight = input.nextDouble();

        // Prompt the user to enter height in inches
        System.out.print("Enter height in two values - feet and inches: ");
        double height = calculateHeight(input.nextDouble(), input.nextDouble());

        // Display result
        double bmi = calculateBMI(weight, height);
        System.out.println("BMI is " + Math.round(bmi * 100.0) / 100.0);
        System.out.println(interpretBMI(bmi));
    }

    public static double calculateHeight(double f, double i) {
        return (f * 12) + i;
    }

    public static double calculateBMI(double w, double h) {
        final double KILOGRAMS_PER_POUND = 0.45359237; // Constant
        final double METERS_PER_INCH = 0.0254; // Constant

        // Convert to metric units
        double weightInKilograms = w * KILOGRAMS_PER_POUND;
        double heightInMeters = h * METERS_PER_INCH;

        // Calculate and return BMI
        return weightInKilograms / (heightInMeters * heightInMeters);
    }

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
