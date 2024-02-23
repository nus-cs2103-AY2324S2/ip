package talktomeorilldie;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * JUnit test for Task class.
 */
public class TaskTest {

    /**
     * Tests the MarkAsDone toString method of Task.
     */
    @Test
    public void testMarkAsDone() {
        Task task = new Task("Test Task");
        task.markAsDone();
        assertEquals("[X] Test Task", task.toString());
    }

    /**
     * Tests the MarkAsNotDone toString method of Task.
     */
    @Test
    public void testMarkAsNotDone() {
        Task task = new Task("Test Task");
        task.markAsDone();
        task.markAsNotDone();
        assertEquals("[ ] Test Task", task.toString());
    }
}
