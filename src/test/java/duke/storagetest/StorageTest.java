package duke.storagetest;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.ToDos;
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

public class StorageTest {
    private Parser parser;
    private TaskList list;
    private Storage storage;
    
    @TempDir
    Path tempDir;
    
    @BeforeEach
    void setUp() {
        parser = new Parser();
        list = new TaskList(new ArrayList<>());
        storage = new Storage(tempDir.resolve("dukeTest.txt").toString(), parser);
    }
    
    @Test
    void testLoadFile_emptyFile() throws IOException {
        Files.createFile(tempDir.resolve("dukeTest.txt"));
        TaskList loadedList = storage.loadFile(new TaskList(new ArrayList<>()));
        assertTrue(loadedList.getList().isEmpty(), "Loaded list should be empty for an empty file");
    }
    
    @Test
    void testAddTaskToFile() throws IOException {
        Task task = new ToDos("running");
        storage.addTaskToFile(task);
        String content = Files.readString(tempDir.resolve("dukeTest.txt"));
        assertTrue(content.contains("running"), "File should contain the added task's description");
    }
    
    @Test
    void testChangeFileContent() throws IOException {
        Task task1 = new ToDos("sleep");
        Task task2 = new ToDos("eat");
        list.add(task1);
        list.add(task2);
        storage.changeFileContent(list);
        String content = Files.readString(tempDir.resolve("dukeTest.txt"));
        assertTrue(content.contains("sleep") && content.contains("eat"), "File should contain both tasks' descriptions");
    }
}

