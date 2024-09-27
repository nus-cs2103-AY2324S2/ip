package dino.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dino.Storage;
import dino.TaskList;
import dino.task.Deadline;
import dino.task.Event;
import dino.task.ToDo;

public class StorageTest {

    private static final String TEST_FILE_PATH = "test_data/testFile.txt";

    @BeforeAll
    public static void setUp() {
        File directory = new File("test_data");
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    @Test
    public void testLoadTasksFromFile() {
        try {
            createTestDataFile();

            Storage storage = new Storage(TEST_FILE_PATH);
            TaskList loadedTaskList = storage.loadTasksFromFile();

            assertEquals(3, loadedTaskList.size());
            assertTrue(loadedTaskList.get(0) instanceof ToDo);
            assertTrue(loadedTaskList.get(1) instanceof Deadline);
            assertTrue(loadedTaskList.get(2) instanceof Event);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createTestDataFile() throws IOException {
        File file = new File(TEST_FILE_PATH);
        if (!file.exists()) {
            file.createNewFile();
        }

        try (PrintStream printStream = new PrintStream(new FileOutputStream(file))) {
            printStream.println(" T | 0 | Read book");
            printStream.println(" D | 0 | Return book | by: Jan 01 2022 12:34");
            printStream.println(" E | 0 | School | from: Jan 02 2022 12:00 to: Jan 03 2022 14:00");
        }
    }
}
