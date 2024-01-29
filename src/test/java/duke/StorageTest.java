package duke;

import model.Task;
import model.ToDo;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {
    @Test
    void isFileExistsTest_returnsTrue() {
        String filePath = "test.dat";
        Storage storage = new Storage(filePath);

        storage.createNewFile();

        assertTrue(storage.isFileExists());

        File file = new File(filePath);
        assertTrue(file.delete());
    }

    @Test
    void isFileExistsTest_returnsFalse() {
        String filePath = "test.dat";
        Storage storage = new Storage(filePath);

        assertFalse(storage.isFileExists());
    }

    @Test
    void createNewFileTest_returnsTrue() {
        String filePath = "test.dat";
        Storage storage = new Storage(filePath);

        storage.createNewFile();

        assertTrue(storage.isFileExists());

        File file = new File(filePath);
        assertTrue(file.delete());
    }

    @Test
    void updateAndLoadTest_returnsTrue() {
        String filePath = "test.dat";
        Storage storage = new Storage(filePath);
        ArrayList<Task> originalTaskList = new ArrayList<>(100);
        originalTaskList.add(new ToDo("todo"));

        storage.update(originalTaskList);
        ArrayList<Task> loadedTaskList = storage.load();

        assertEquals(originalTaskList.size(), loadedTaskList.size());

        File file = new File(filePath);
        assertTrue(file.delete());
    }
}
