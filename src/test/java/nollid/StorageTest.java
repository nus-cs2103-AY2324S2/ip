package nollid;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import nollid.tasks.Task;

public class StorageTest {
    @Test
    public void load_noFile_emptyListReturned() {
        Path invalidFile = Paths.get(".", "data", "tests", "filethatshouldnt.exist");

        // Delete the file if it exists.
        try {
            Files.deleteIfExists(invalidFile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        Storage s = new Storage(invalidFile);
        ArrayList<Task> tasks = s.load();

        int expected = 0;
        int actual = tasks.size();
        assertEquals(expected, actual);
    }

    @Test
    public void load_emptyFile_emptyListReturned() {
        Path emptyFile = Paths.get(".", "data", "tests", "test_empty.json");

        Storage s = new Storage(emptyFile);
        ArrayList<Task> tasks = s.load();

        int expected = 0;
        int actual = tasks.size();
        assertEquals(expected, actual);
    }

    @Test
    public void load_invalidJson_emptyListReturned() {
        Path emptyFile = Paths.get(".", "data", "tests", "test_invalid.json");

        Storage s = new Storage(emptyFile);
        ArrayList<Task> tasks = s.load();

        int expected = 0;
        int actual = tasks.size();
        assertEquals(expected, actual);
    }

    @Test
    public void load_validJson_listInitialized() {
        Path emptyFile = Paths.get(".", "data", "tests", "test_valid.json");

        Storage s = new Storage(emptyFile);
        ArrayList<Task> tasks = s.load();

        int expected = 3;
        int actual = tasks.size();
        assertEquals(expected, actual);
    }

    @Test
    public void load_malformedTask_listPartiallyInitialized() {
        Path emptyFile = Paths.get(".", "data", "tests", "test_partiallyValid.json");

        Storage s = new Storage(emptyFile);
        ArrayList<Task> tasks = s.load();

        int expected = 2;
        int actual = tasks.size();
        assertEquals(expected, actual);
    }
}
