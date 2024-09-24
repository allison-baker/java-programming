// Project Prolog
// Name: Al Baker
// CS3250 Section 601
// Project: Assignment 04
// Date: 09/22/2024
// Purpose: Create a word data object for occurrences, indexes, and capital occurrences.

import java.util.List;
import java.util.LinkedList;

public class WordData {
    private String word;
    private List<Tuple> occurrences = new LinkedList<>();

    public WordData(String word, int index, boolean capitalized) {
        this.word = word;
        addOccurrence(index, capitalized);
    }

    public String getWord() {
        return word;
    }

    public List<Tuple> getOccurrences() {
        return occurrences;
    }

    public int getCount() { return occurrences.size(); }

    public void addOccurrence (int index, boolean capitalized) {
        Tuple newOccurrence = new Tuple(index, capitalized);
        occurrences.add(newOccurrence);
    }
}
