// Project Prolog
// Name: Al Baker
// CS3250 Section 601
// Project: Assignment 08
// Date: 09/30/2024
// Purpose: Hold the puzzle part of the word search program.

public class Puzzle {
    Character[][] grid;
    int size;

    public Puzzle(int size) {
        grid = new Character[size][size];
        this.size = size;
    }

    public void addChar(char c, int row, int col) { grid[row][col] = c; }

    public int getSize() { return size; }

    public char getCharAt(int row, int col) { return grid[row][col]; }
}
