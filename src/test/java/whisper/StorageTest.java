package whisper;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StorageTest {

    @Test
    void saveFile_validTasks_tasksSavedSuccessfully() throws IOException, WhisperException {
        // setup
        Path tempFile = Files.createTempFile("tempTasks", ".txt");
        String filePath = tempFile.toString();
        Storage storage = new Storage(filePath);

        // sample tasks
        ArrayList<Task> tasksToSave = new ArrayList<>();
        Task task1 = new Task("Task 1", Task.TaskCategory.T);
        Task task2 = new Task("Task 2", Task.TaskCategory.D, LocalDateTime.now());
        Task task3 = new Task("Task 3", Task.TaskCategory.E, LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        tasksToSave.add(task1);
        tasksToSave.add(task2);
        tasksToSave.add(task3);

        // execute
        storage.saveFile(tasksToSave);

        // read the saved tasks
        List<String> lines = Files.readAllLines(tempFile);

        // verify
        assertEquals(tasksToSave.size(), lines.size());
        for (int i = 0; i < tasksToSave.size(); i++) {
            assertEquals(storage.taskToFormattedString(tasksToSave.get(i)), lines.get(i));
        }

        // clean
        Files.deleteIfExists(tempFile);
    }

    @Test
    void parseTaskFromString_invalidInput_exceptionThrown() {
        // setup
        Storage storage = new Storage("dummy/path");
        String invalidInput = "Invalid Input";

        // verify
        assertThrows(WhisperException.class, () -> storage.parseTaskFromString(invalidInput));
    }

    @Test
    void parseDateTime_validDateTime_correctlyParsed() throws WhisperException {
        // setup
        Storage storage = new Storage("dummy/path");
        String dateTimeString = "01/01/2022 12:30";

        // execute
        LocalDateTime parsedDateTime = storage.parseDateTime(dateTimeString);

        // verify
        LocalDateTime expectedDateTime = LocalDateTime.of(2022, 1, 1, 12, 30);
        assertEquals(expectedDateTime, parsedDateTime);
    }

    @Test
    void parseDateTime_invalidDateTime_exceptionThrown() {
        // setup
        Storage storage = new Storage("dummy/path");
        String invalidDateTimeString = "Invalid DateTime";

        // verify
        assertThrows(WhisperException.class, () -> storage.parseDateTime(invalidDateTimeString));
    }
}
