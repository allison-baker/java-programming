// Project Prolog
// Name: Al Baker
// CS3250 Section 601
// Project: Assignment 12
// Date: 11/04/2024
// Purpose: Create a tuple class for the word data object.

package com.almariebaker.assignment12;

public class Tuple {
    private int index;
    private boolean capitalized;

    public Tuple(int index, boolean capitalized) {
        this.index = index;
        this.capitalized = capitalized;
    }

    public int getIndex() { return index; }

    public boolean isCapitalized() { return capitalized; }
}
