// Project Prolog
// Name: Al Baker
// CS3250 Section 601
// Project: Assignment 12
// Date: 11/04/2024
// Purpose: Test the file scraper and indexing program.

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.almariebaker.assignment12.Tuple;
import com.almariebaker.assignment12.WordData;
import com.almariebaker.assignment12.Main;

public class MainTest {
    static Map<String, WordData> sampleMap = new LinkedHashMap<>();
    static List<String> sampleLines = new ArrayList<>();

    @BeforeAll
    static void populateSample() {
        sampleMap.put("this", new WordData("this", 0, true));
        sampleMap.put("is", new WordData("is", 1, false));
        sampleMap.put("a", new WordData("a", 2, false));
        sampleMap.put("test", new WordData("test", 3, false));
        sampleMap.put("file", new WordData("file", 4, false));
        sampleMap.put("here", new WordData("here", 5, true));
        sampleMap.put("are", new WordData("are", 6, false));
        sampleMap.put("more", new WordData("more", 7, false));
        sampleMap.put("words", new WordData("words", 8, false));
        sampleMap.put("repeated", new WordData("repeated", 9, true));
        sampleMap.get("words").addOccurrence(10, false);
        sampleMap.get("a").addOccurrence(11, false);
        sampleMap.get("is").addOccurrence(12, true);
        sampleMap.get("this").addOccurrence(13, false);
        sampleMap.get("file").addOccurrence(14, true);

        sampleLines.add("This is a test file.");
        sampleLines.add("Here are more words.");
        sampleLines.add("Repeated words.");
        sampleLines.add("a Is this File.");
    }

    // Test that the program reads the file correctly
    @Test
    public void testReadFile() {
        List<String> testLines = Main.readFile("src/test/resources/test.txt");
        assertIterableEquals(sampleLines, testLines);
    }

    // Test that the program populates the map correctly
    @Test
    public void testPopulateMap() {
        Map<String, WordData> testMap = Main.populateMap(sampleLines);
        for (Map.Entry<String, WordData> entry : testMap.entrySet()) {
            assertAll("map entry",
                    () -> assertEquals(sampleMap.get(entry.getKey()).getWord(), entry.getValue().getWord()),
                    () -> {
                        for (int i = 0; i < entry.getValue().getOccurrences().size(); i++) {
                            Tuple sampleOccurrence = sampleMap.get(entry.getKey()).getOccurrences().get(i);
                            int finalI = i;
                            assertAll("occurrence",
                                    () -> assertEquals(sampleOccurrence.getIndex(), entry.getValue().getOccurrences().get(finalI).getIndex()),
                                    () -> assertEquals(sampleOccurrence.isCapitalized(), entry.getValue().getOccurrences().get(finalI).isCapitalized())
                            );
                        }
                    }
            );
        }
    }

    // Test that the program prints the correct number of words in alphabetical order
    @Test
    public void testPrintAlpha() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        Main.printAlpha(5, sampleMap);
        String expected =
                "\nFirst 5 words, alphabetically:\n"
                        + "\"Word\"" + System.lineSeparator() + "\tPosition, Capitalized" + System.lineSeparator()
                        + "\"a\"\n\t2 false\n\t11 false\n"
                        + "\"file\"\n\t4 false\n\t14 true\n"
                        + "\"is\"\n\t1 false\n\t12 true\n"
                        + "\"test\"\n\t3 false\n"
                        + "\"this\"\n\t0 true\n\t13 false\n";
        assertEquals(expected, outputStream.toString());

        System.setOut(originalOut);
    }

    // Test that the program prints the correct number of words by count
    @Test
    public void testPrintByCount() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        Main.printByCount(5, sampleMap);
        String expected =
                "\n5 words by count: \"Word\", Count\n"
                        + "\"this\" 2\n"
                        + "\"is\" 2\n"
                        + "\"a\" 2\n"
                        + "\"file\" 2\n"
                        + "\"words\" 2\n";
        assertEquals(expected, outputStream.toString());

        System.setOut(originalOut);
    }
}
