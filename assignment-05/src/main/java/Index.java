// Project Prolog
// Name: Al Baker
// CS3250 Section 601
// Project: Assignment 04
// Date: 09/22/2024
// Purpose: Scrape a text file and create a search index for each word in the file.

import java.util.*;

class Occurrence {
    int index;
    boolean capitalized;
}

public class Index {
    List<Occurrence> occurrences;

    Index(int index, boolean capitalized) {
        occurrences = new ArrayList<>();
        addOccurrence(index, capitalized);
    }

    public int getSize() {
        return occurrences.size();
    }

    public void addOccurrence(int index, boolean capitalized) {
        Occurrence occurrence = new Occurrence();
        occurrence.index = index;
        occurrence.capitalized = capitalized;
        occurrences.add(occurrence);
    }
}
