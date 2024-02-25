package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class StorageTest {
    private String filePath = "data/duketest.txt";
    private Storage testStorage = new Storage(filePath);

    @Test
    public void testReadWriteLines() {
        List<String> input = new ArrayList<>();

        input.add("E | 0 | project meeting  | 01 October 2023 00:00 | 01 December 2024 23:59");
        input.add("E | 1 | long project meeting  | 01 May 2000 00:01 | 01 May 2020 10:00");
        input.add("D | 0 | return book | 01 May 2020 10:00");
        input.add("D | 0 | do homework | no idea :-p");
        input.add("E | 0 | project meeting 2  | Mon 2pm | 5pm");
        input.add("D | 0 | return book | today");
        testStorage.writeLinesToFile(input);

        List<String> output = testStorage.readLinesFromFile();

        assertEquals(input, output);
    }
}
