package bytebuddy.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


public class TaskTest {

    @Test
    public void testTaskMarkAsDone() {
        Task task = new Todo("Sample task");

        String expectedOutput = "Nice! I've mark this task as done:\n\t\t[T][✓] " + task.description;
        String actualOutput = task.markAsDone();

        assertEquals(expectedOutput, actualOutput);
        assertTrue(task.isDone);
    }

    @Test
    public void testTaskUnmarkAsDone() {
        Task task = new Todo("1", "Sample task");

        String expectedOutput = "OK, I've marked this task as not done yet:\n\t\t[T][✕] " + task.description;
        String actualOutput = task.unmarkAsDone();

        assertEquals(expectedOutput, actualOutput);
        assertFalse(task.isDone);
    }

    @Test
    public void testGetStatusIcon() {
        Task task = new Todo("Sample task");
        task.markAsDone();

        String expectedOutput = "✓";
        String actualOutput = task.getStatusIcon();

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testTaskToString() {
        Task task = new Todo("1", "Sample task");

        String expectedOutput = "[T][" + task.getStatusIcon() + "] Sample task";
        String actualOutput = task.toString();

        assertEquals(expectedOutput, actualOutput);
    }
}

