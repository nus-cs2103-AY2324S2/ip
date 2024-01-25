package duke;

import model.Task;
import model.ToDo;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {
    @Test
    void isFileExistsTest() {
        String filePath = "test.dat";
        Storage storage = new Storage(filePath);

        assertFalse(storage.isFileExists());

        storage.createNewFile();

        assertTrue(storage.isFileExists());

        File file = new File(filePath);
        assertTrue(file.delete());
    }

    @Test
    void createNewFileTest() {
        String filePath = "test.dat";
        Storage storage = new Storage(filePath);

        storage.createNewFile();

        assertTrue(storage.isFileExists());

        File file = new File(filePath);
        assertTrue(file.delete());
    }

    @Test
    void updateAndLoadTest() {
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
