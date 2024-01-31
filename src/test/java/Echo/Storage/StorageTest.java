package Echo.Storage;

import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import Echo.Task.Task;
import Echo.Task.Todo;
import Echo.Task.Deadline;
import Echo.Task.Event;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StorageTest {

    @Test
    void testSave() {
        String filePath = "testSaveFile.txt";
        Storage storage = new Storage(filePath);

        List<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("Test task 1"));
        tasks.add(new Deadline("Test task 2", "2022-12-31 1500"));

        storage.save(tasks);

        // Read the content of the file and compare it with the expected content
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

            // Check the number of lines
            assertEquals(2, lines.size());

            assertTrue(lines.get(0).contains("Test task 1"));
            assertTrue(lines.get(1).contains("Test task 2"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Clean up: Delete the temporary file
            try {
                Files.deleteIfExists(Path.of(filePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

