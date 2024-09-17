// In class example - 16 September 2024
// Java Streams

import java.util.*;

public class Streams {
    public static void main(String[] args) {
        // Creating an ArrayList object of integer type
        ArrayList<Integer> numbers = new ArrayList<Integer>();

        // Inserting elements ot ArrayList object
        // Custom input integer numbers
        numbers.add(2);
        numbers.add(6);
        numbers.add(9);
        numbers.add(4);
        numbers.add(20);
        numbers.add(21);
        numbers.add(100);
        numbers.add(-1);
        numbers.add(45);
        numbers.add(7);

        numbers.stream()
            .filter((i) -> i > 5)
            .map((i) -> i * i)
            .filter((i) -> i % 2 == 0)
            .map((x) -> Integer.toString(x))
            .forEach(System.out::println);
    }
}
