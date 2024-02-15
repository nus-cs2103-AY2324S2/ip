package duke;

//import duke.task.*;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class StorageTest {

    @TempDir
    Path tempDir;

    @Test
    void save() {
        Path tempFile = tempDir.resolve("temp.txt");
        Storage storage = new Storage(tempFile.toString());
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDo("Write JUnit tests"));

        // Handle or declare the potential IOException
        assertDoesNotThrow(() -> {
            try {
                storage.save(taskList);
            } catch (IOException e) {
                // Handle the IOException
                throw new RuntimeException(e);
            }
            // Remove the catch block for JamieException if it's not thrown by storage.save
        });
        // Additional code to check the file's contents can be added here
    }
}
