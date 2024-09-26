package echo;

import echo.storage.Storage;
import echo.task.Task;
import echo.task.Todo;
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

class TaskManagerTest {

    private TaskManager taskManager;
    private final String FILE_PATH = "." + File.separator + "data" + File.separator + "testecho.txt";
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        Storage storage = new Storage(FILE_PATH);
        Echo echo = new Echo();
        taskManager = new TaskManager(storage, echo);
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
        assertFormattedBotResponseContains("Echo: Ok you are never going to finish your tasks: :\n" +
                "  [T][ ] Test Todo Task\n" +
                "Now you have 1 tasks in the list.");
    }

    @Test
    void testDeleteTask() {
        // Given
        String[] addTokens = {"todo", "Test Todo Task"};
        taskManager.addTask(addTokens);  // Adding a task for deletion

        // When
        String[] deleteTokens = {"delete", "1"};
        taskManager.deleteTask(deleteTokens);

        // Then
        List<Task> tasks = taskManager.getTasks();
        assertEquals(0, tasks.size());
        assertFormattedBotResponseContains("Echo: Did you finish it or did you just avoid it?\n" +
                "Removed task  [T][ ] Test Todo Task\n" +
                "Now you have 0 tasks in the list.");
    }

    @Test
    void testListTasksWhenEmpty() {
        // When
        taskManager.listTasks();

        // Then
        String expectedOutput = "Echo: OK Here are the tasks in your list:\nNo tasks in the list.\nYou really can't remember them yourself?";
        assertFormattedBotResponseContains(expectedOutput);
    }

    @Test
    void testAddEventWithConflict() {
        // Given
        String[] existingEventTokens = {"event", "Existing Event Task /from 2024-02-22 1000 /to 2024-02-22 1200"};
        taskManager.addTask(existingEventTokens);  // Adding a task to have existing tasks

        String[] eventTokens = {"event", "Test Event Task /from 2024-02-22 1000 /to 2024-02-22 1200"};

        // When
        taskManager.addTask(eventTokens);

        // Then
        assertFormattedBotResponseContains("Echo: You already have a task at this time dumb!");
    }

    @Test
    void testAddDeadlineWithConflict() {
        // Given
        String[] existingDeadlineTokens = {"deadline", "Existing Deadline Task /by 2024-02-22 1100"};
        taskManager.addTask(existingDeadlineTokens);  // Adding a task to have existing tasks

        String[] deadlineTokens = {"deadline", "Test Deadline Task /by 2024-02-22 1100"};

        // When
        taskManager.addTask(deadlineTokens);

        // Then
        assertFormattedBotResponseContains("Echo: You already have a task at this time dumb!");
    }

    @Test
    void testAddNonConflictingEvent() {
        // Given
        String[] eventTokens = {"event", "Test Event Task /from 2024-02-22 1400 /to 2024-02-22 1600"};

        // When
        taskManager.addTask(eventTokens);

        // Then
        assertFormattedBotResponseContains("Echo: Ok you are never going to finish your tasks: :\n" +
                "  [E][ ] Test Event Task (from: Feb 22 2024 14:00 to: Feb 22 2024 16:00)\n" +
                "Now you have 1 tasks in the list.");
    }

    @Test
    void testAddNonConflictingDeadline() {
        // Given
        String[] deadlineTokens = {"deadline", "Test Deadline Task /by 2024-02-22 1800"};

        // When
        taskManager.addTask(deadlineTokens);

        // Then
        assertFormattedBotResponseContains("Echo: Ok you are never going to finish your tasks: :\n" +
                "  [D][ ] Test Deadline Task (by: Feb 22 2024 18:00)\n" +
                "Now you have 1 tasks in the list.");
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

    private void assertFormattedBotResponseContains(String expectedContent) {
        assertEquals(expectedContent.trim(), taskManager.getEcho().getFormattedBotResponse().trim());
    }

    private String getFormattedBotResponse() {
        return taskManager.getEcho().getFormattedBotResponse();
    }
}
