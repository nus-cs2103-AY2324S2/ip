package duke.storagetest;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.ToDo;
import duke.tasklist.TaskList;
import duke.task.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for the Storage class functionality.
 * This includes testing the loading of tasks from a file,
 * adding tasks to a file, and changing the content of the file.
 */
public class StorageTest {
    private Parser parser;
    private TaskList list;
    private Storage storage;
    
    @TempDir
    Path tempDir;
    
    /**
     * Sets up the environment for each test.
     * Initializes a new Parser, TaskList, and Storage with a temporary file.
     */
    @BeforeEach
    void setUp() {
        parser = new Parser();
        list = new TaskList(new ArrayList<>());
        storage = new Storage(tempDir.resolve("dukeTest.txt").toString(), parser);
    }
    
    /**
     * Tests loading tasks from an empty file.
     * Verifies that the loaded TaskList is empty.
     *
     * @throws IOException If an I/O error occurs creating the empty file.
     */
    @Test
    void testLoadFile_emptyFile() throws IOException {
        Files.createFile(tempDir.resolve("dukeTest.txt"));
        TaskList loadedList = storage.loadFile(new TaskList(new ArrayList<>()));
        assertTrue(loadedList.getList().isEmpty(), "Loaded list should be empty for an empty file");
    }
    
    /**
     * Tests adding a single task to the file.
     * Verifies that the file contains the description of the added task.
     *
     * @throws IOException If an I/O error occurs reading the file content.
     */
    @Test
    void testAddTaskToFile() throws IOException {
        Task task = new ToDo("running");
        storage.addTaskToFile(task);
        String content = Files.readString(tempDir.resolve("dukeTest.txt"));
        assertTrue(content.contains("running"), "File should contain the added task's description");
    }
    
    /**
     * Tests changing the file content with a new list of tasks.
     * Verifies that the file contains the descriptions of all tasks in the list.
     *
     * @throws IOException If an I/O error occurs reading the file content.
     */
    @Test
    void testChangeFileContent() throws IOException {
        Task task1 = new ToDo("sleep");
        Task task2 = new ToDo("eat");
        list.add(task1);
        list.add(task2);
        storage.changeFileContent(list);
        String content = Files.readString(tempDir.resolve("dukeTest.txt"));
        assertTrue(content.contains("sleep") && content.contains("eat"), "File should contain both tasks' descriptions");
    }
}

