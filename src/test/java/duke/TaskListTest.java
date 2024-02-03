package duke;

import duke.task.Task;
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

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void testAddToListWithString() {
        TaskList list = new TaskList();
        String taskDescription = "test task";
        list.addToList(taskDescription);

        String expectedOutput = "added: test task\n";
        assertEquals(expectedOutput, outputStreamCaptor.toString().replace("\r\n", "\n"));
    }

    @Test
    public void testAddToListWithTask() {
        TaskList list = new TaskList();
        Task newTask = new Task("new task");
        list.addToList(newTask);

        String expectedOutput = "Got it. I've added this duke.task:\n" + newTask.toString() + "\n" +
                "Now you have 1 tasks in the list.\n";
        assertEquals(expectedOutput, outputStreamCaptor.toString().replace("\r\n", "\n"));
    }
}
