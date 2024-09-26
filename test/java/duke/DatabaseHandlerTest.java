package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class StorageTest {
    private String storedPath = "data/duketest.txt";
    private Storage testStorage = new Storage(storedPath);

    @Test
    public void testReadWriteOps() {

        List<String> input = new ArrayList<>();

        input.add("E | 0 | CS2103T Lecture | 23 February 2024 16:00 | 23 February 2024 18:00");
	input.add("E | 1 | Group Project Milestone Meeting | 1 March 2024 14:00 | 1 March 2024 16:00");
	input.add("D | 0 | Submit Individual Project | 23 February 2024 23:59");

        testStorage.writeOps(input);

        List<String> output = testStorage.readOps();

        assertEquals(input, output);
    }
}
