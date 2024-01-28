// used chatGPT to create these unit tests

package chatbot;
import java.io.*;
import java.util.*;
import java.nio.file.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


class StorageTest {

    private String testFilePath;
    private Storage storage;

    @BeforeEach
    void setUp() {
        testFilePath = "./temp_test_file.txt";
        storage = new Storage(testFilePath);
    }

    @AfterEach
    void tearDown() {
        try {
            Files.deleteIfExists(Paths.get(testFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testFileCreation() {
        assertFalse(Files.exists(Paths.get(testFilePath)));
        storage.createFileIfNeeded();
        assertTrue(Files.exists(Paths.get(testFilePath)));
    }

    @Test
    void testRewriteFile() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Task 1"));
        tasks.add(new Task("Task 2"));

        storage.rewriteFile(tasks);

        List<String> lines = Files.readAllLines(Paths.get(testFilePath));
        assertEquals(2, lines.size());
        assertEquals("[ ] Task 1", lines.get(0));
        assertEquals("[ ] Task 2", lines.get(1));
    }

}
