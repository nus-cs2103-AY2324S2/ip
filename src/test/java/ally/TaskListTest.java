package ally;

import ally.task.Task;
import ally.utils.TaskList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test for Storage class used in Duke.
 */
public class TaskListTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    /**
     * setUp PrintStream for testing.
     */
    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    /**
     * Tear down PrintStream after testing
     * This is to not interfere with functions after testing
     */
    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    /**
     * Tests addition of task to list.
     */
    @Test
    public void testAddToListWithTask() {
        TaskList list = new TaskList();
        Task newTask = new Task("new task");
        String expectedOutput = "Got it. I've added this task:\n" + newTask + "\n" +
                "Now you have 1 tasks in the list.";
        assertEquals(expectedOutput, list.addToList(newTask));
    }
}
