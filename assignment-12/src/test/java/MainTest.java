// Project Prolog
// Name: Al Baker
// CS3250 Section 601
// Project: Assignment 12
// Date: 11/04/2024
// Purpose: Test the file scraper and indexing program.

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.almariebaker.assignment12.*;

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
        List<String> testLines = new ArrayList<>();

        try {
            testLines = Main.readFile("src/test/resources/test.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

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
}
