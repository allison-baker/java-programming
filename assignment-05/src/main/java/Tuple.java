// Project Prolog
// Name: Al Baker
// CS3250 Section 601
// Project: Assignment 05
// Date: 09/23/2024
// Purpose: Create a tuple class for the word data object.

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
