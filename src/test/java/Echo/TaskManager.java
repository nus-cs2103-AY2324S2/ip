package Echo;

import Echo.Storage.Storage;
import Echo.Task.Task;
import Echo.Task.Todo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class TaskManagerTest {

    private TaskManager taskManager;
    private final String FILE_PATH = "." + File.separator + "data" + File.separator + "testecho.txt";
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        Storage storage = new Storage(FILE_PATH);
        taskManager = new TaskManager(storage);
    }

    @Test
    void testAddTodoTask() {
        // Given
        String[] tokens = {"todo", "Test Todo Task"};

        // When
        taskManager.addTask(tokens);

        // Then
        List<Task> tasks = taskManager.getTasks();
        assertEquals(1, tasks.size());
        assertEquals("Test Todo Task", tasks.get(0).getDescription());
        System.setOut(new PrintStream(outputStreamCaptor));
        System.setErr(new PrintStream(outputStreamCaptor));
    }

    @Test
    void testDeleteTask() {
        String[] addTokens = {"todo", "Test Todo Task"};
        taskManager.addTask(addTokens);  // Adding a task for deletion

        String[] deleteTokens = {"delete", "1"};

        taskManager.deleteTask(deleteTokens);

        List<Task> tasks = taskManager.getTasks();
        assertEquals(0, tasks.size());
    }

    @Test
    void testListTasksWhenEmpty() {
        taskManager.listTasks();
        String expectedOutput = "";
        assertEquals(expectedOutput, getSystemOut());
    }


    @AfterEach
    void tearDown() {
        resetFile();
    }

    private void resetFile() {
        try {
            File file = new File(FILE_PATH);

            // Create a new file writer with append mode set to false (clears existing content)
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) {
                // Clear the content of the test file
                writer.write("");
                System.out.println("Test file content cleared successfully.");
            }

        } catch (IOException e) {
            System.out.println("Error resetting test file: " + e.getMessage());
        }
    }

    private String getSystemOut() {
        return outputStreamCaptor.toString().trim();
    }
}
