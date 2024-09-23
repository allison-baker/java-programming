// Project Prolog
// Name: Al Baker
// CS3250 Section 601
// Project: Assignment 04
// Date: 09/22/2024
// Purpose: Create a word data object for occurrences, indexes, and capital occurrences.

import java.util.HashMap;
import java.util.Map;

public class WordData {
    private String word;
    private Map<Integer, Boolean> occurrences = new HashMap<>();
    private int count = 0;

    public WordData(String word, int index, boolean capitalized) {
        this.word = word;
        occurrences.put(index, capitalized);
        count++;
    }

    public String getWord() {
        return word;
    }

    public Map<Integer, Boolean> getOccurrences() {
        return occurrences;
    }

    public int getCount() {
        return count;
    }

    public void addOccurrences (int index, boolean capitalized) {
        occurrences.put(index, capitalized);
        count++;
    }
}
