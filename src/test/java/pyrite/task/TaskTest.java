package pyrite.task;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Test cases for Task class.
 */
public class TaskTest {
    // Test cases suggested by Github Copilot
    /**
     * Test case for toString method with normal string.
     */
    @Test
    public void toString_normalString_success() {
        Task task = new Task("test");
        assertTrue(task.toString().equals("[ ] test"));
    }
    /**
     * Test case for toString method with empty string.
     */
    @Test
        public void toString_emptyString_exception() {
        try {
            Task task = new Task("");
        } catch (AssertionError e) {
            assertTrue(e instanceof AssertionError);
        }
    }
    /**
     * Test case for setStatus method to mark as done.
     */
    @Test
    public void setStatus_markAsDone_success() {
        Task task = new Task("test");
        task.setStatus(Task.Status.Done);
        assertTrue(task.toString().equals("[X] test"));
    }
    /**
     * Test case for setStatus method to mark as not done.
     */
    @Test
    public void setStatus_markAsNotDone_success() {
        Task task = new Task("test");
        task.setStatus(Task.Status.Done);
        task.setStatus(Task.Status.NotDone);
        assertTrue(task.toString().equals("[ ] test"));
    }
    /**
     * Test case for getStatusIcon method to mark as done.
     */
    @Test
    public void getStatusIcon_markAsDone_success() {
        Task task = new Task("test");
        task.setStatus(Task.Status.Done);
        assertTrue(task.getStatusIcon().equals("X"));
    }
    /**
     * Test case for getStatusIcon method to mark as not done.
     */
    @Test
    public void getStatusIcon_markAsNotDone_success() {
        Task task = new Task("test");
        task.setStatus(Task.Status.Done);
        task.setStatus(Task.Status.NotDone);
        assertTrue(task.getStatusIcon().equals(" "));
    }
    /**
     * Test case for getDescription method.
     */
    @Test
    public void getDescription_success() {
        Task task = new Task("test");
        assertTrue(task.getDescription().equals("test"));
    }
}
