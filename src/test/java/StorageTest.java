import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.Storage;
import duke.Task;
import duke.TaskList;

class StorageTest {
    private Path tempFile;
    private Storage storage;

    @BeforeEach
    void setUp() throws IOException, DukeException {
        // Create a temporary file
        tempFile = Files.createTempFile("test", ".txt");
        // Initialize Storage with the temporary file path
        storage = new Storage(tempFile.toString());
    }

    @AfterEach
    void tearDown() throws IOException {
        // Delete the temporary file after each test
        Files.deleteIfExists(tempFile);
    }

    @Test
    void saveAndLoadTodoTask() throws IOException, DukeException {
        TaskList list = new TaskList();
        list.add(new Task("read book")); // Assuming Task is a valid type for simplicity

        // Save the list to the file
        Storage.saveCurrentList(list);

        // Load the list back
        ArrayList<Task> loadedList = storage.load();

        // Check if the loaded list matches the saved list
        assertEquals(1, loadedList.size());
        assertEquals("read book", loadedList.get(0).getDescription());
    }

}
