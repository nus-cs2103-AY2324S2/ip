package storage;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class StorageTest {
    private static final String TEST_PATH = "./testdata/data.txt";

    @BeforeAll
    public static void beforeAll() {
        Path path = Paths.get(TEST_PATH);
        try {
            Files.deleteIfExists(path);
            Files.deleteIfExists(path.getParent());
        } catch (IOException e) {
            fail("Failed to delete files at setup");
        }
    }

    @Test
    public void initializeStorageTest() {
        Storage storage = new Storage();
        assertEquals(storage.pathString, "./data/data.txt");
        assertEquals(storage.path, Paths.get("./data/data.txt"));
    }

    @Test
    public void changePathTest() {
        Storage storage = new Storage();
        storage.switchPath(TEST_PATH);
        assertEquals(storage.pathString, TEST_PATH);
        assertEquals(storage.path, Paths.get(TEST_PATH));
    }

    @Test
    public void alternateConstructorTest() {
        Storage storage = new Storage(TEST_PATH);
        assertEquals(storage.pathString, TEST_PATH);
        assertEquals(storage.path, Paths.get(TEST_PATH));
    }

    @Test
    public void equalsTest() {
        Storage storage = new Storage(TEST_PATH);
        Storage storage2 = null;
        assertFalse(storage.equals(storage2));
        assertFalse(storage.equals(new Object()));
        storage2 = new Storage();
        assertFalse(storage.equals(storage2));
        storage2.switchPath(TEST_PATH);
        assertTrue(storage.equals(storage2));
    }

    @Test
    public void createPathIfMissingTest() {
        Storage storage = new Storage(TEST_PATH);
        assertDoesNotThrow(() -> storage.createPathIfMissing());
        assertTrue(Files.exists(storage.path));
        assertDoesNotThrow(() -> storage.createPathIfMissing());
    }

    @Test
    public void saveLoadTest() {
        List<String> tasks = new ArrayList<>(10);
        tasks.add("First Task");
        tasks.add("Second Task");
        Storage storage = new Storage(TEST_PATH);
        List<String> returnedTasks;

        assertDoesNotThrow(() -> storage.save(tasks));
        assertDoesNotThrow(() -> assertEquals(storage.load(), tasks));
    }
}
