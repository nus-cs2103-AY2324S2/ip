package bytebuddy.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    @Test
    public void testTaskMarkAsDone() {
        Task task = new Task("Sample task");

        String expectedOutput = "Nice! I've mark this task as done:\n\t\t[✓] " + task.description;
        String actualOutput = task.markAsDone();

        assertEquals(expectedOutput, actualOutput);
        assertTrue(task.isDone);
    }

    @Test
    public void testTaskUnmarkAsDone() {
        Task task = new Task("Sample task", true);

        String expectedOutput = "OK, I've marked this task as not done yet:\n\t\t[✕] " + task.description;
        String actualOutput = task.unmarkAsDone();

        assertEquals(expectedOutput, actualOutput);
        assertFalse(task.isDone);
    }

    @Test
    public void testGetStatusIcon() {
        Task task = new Task("Sample task");
        task.markAsDone();

        String expectedOutput = "✓";
        String actualOutput = task.getStatusIcon();

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testTaskToString() {
        Task task = new Task("Sample task", true);

        String expectedOutput = "[" + task.getStatusIcon() + "] Sample task";
        String actualOutput = task.toString();

        assertEquals(expectedOutput, actualOutput);
    }
}

