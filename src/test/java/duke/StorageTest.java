package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import iggly.duke.Storage;
import iggly.model.Task;
import iggly.model.ToDo;

public class StorageTest {
    @Test
    void isFileExistsTest_returnsTrue() {
        String filePath = "test.dat";
        Storage storage = new Storage(filePath);

        storage.createNewFile();

        assertTrue(storage.isExistingFile());

        File file = new File(filePath);
        assertTrue(file.delete());
    }

    @Test
    void isFileExistsTest_returnsFalse() {
        String filePath = "test.dat";
        Storage storage = new Storage(filePath);

        assertFalse(storage.isExistingFile());
    }

    @Test
    void createNewFileTest_returnsTrue() {
        String filePath = "test.dat";
        Storage storage = new Storage(filePath);

        storage.createNewFile();

        assertTrue(storage.isExistingFile());

        File file = new File(filePath);
        assertTrue(file.delete());
    }

    @Test
    void updateAndLoadTest_returnsTrue() {
        String filePath = "test.dat";
        Storage storage = new Storage(filePath);
        ArrayList<Task> originalTaskList = new ArrayList<>(100);
        originalTaskList.add(new ToDo("todo"));

        storage.updateFile(originalTaskList);
        ArrayList<Task> loadedTaskList = storage.load();

        assertEquals(originalTaskList.size(), loadedTaskList.size());

        File file = new File(filePath);
        assertTrue(file.delete());
    }
}
