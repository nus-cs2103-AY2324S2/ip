package ally.utils;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import ally.task.Task;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
/**
 * Test for Storage class used in Duke.
 */
public class TaskListTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    /**
     * Tests addition of task to list.
     */
    @Test
    public void testAddToListWithTask() {
        TaskList list = new TaskList();
        Task newTask = new Task("new task");
        String expectedOutput = "Got it. I've added this task:\n" + newTask + "\n"
                + "Now you have 1 tasks in the list.";
        assertEquals(expectedOutput, list.addToList(newTask));
    }

    /**
     * Tests removal of task from list.
     */
    @Test
    public void testRemoveFromList() {
        TaskList list = new TaskList();
        Task newTask = new Task("new task");
        list.addToList(newTask);
        assertEquals(list.markComplete(1), "Nice! I've marked this task as done:\n" + "[X] new task");
        assertEquals(list.unmarkComplete(1), "OK, I've marked this task as not done yet:\n" + "[ ] new task");
        assertEquals(list.delete(1), "Noted. I've removed this task:\n"
                + "[ ] new task\n" + "Now you have 0 tasks in the list.");
    }
}
