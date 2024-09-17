package storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

public class StorageTest {

    private String tempFile;
    private Storage storage;

    @BeforeEach
    public void setUp() throws IOException {
        tempFile = "data/testData.txt";
        storage = new Storage(tempFile);
    }

    @Test
    public void testWriteAndRead() throws Exception {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDo("Sample Task 1", false));
        tasks.add(new Deadline("Sample Task 2", true, "Feb 23 2023 12:00 AM"));
        tasks.add(new Event("Sample Task 3", false, "Mar 27 2023 6:00 PM", "Mar 27 2023 8:00 PM"));

        storage.write(tasks);

        StringBuilder fileContents = new StringBuilder();
        File file = new File("src/main/resources/" + tempFile);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                fileContents.append(line).append("\n");
            }
        }

        String expected = "T | 0 | Sample Task 1\n"
                + "D | 1 | Sample Task 2 | Feb 23 2023 12:00 AM\n"
                + "E | 0 | Sample Task 3 | Mar 27 2023 6:00 PM | Mar 27 2023 8:00 PM\n";
        assertEquals(expected.trim(), fileContents.toString().trim());
    }

    @AfterEach
    public void tearDown() {
        File file = new File("src/main/resources/" + tempFile);
        if (file.exists()) {
            boolean deleted = file.delete();
            assertTrue(deleted);
        }
    }
}
