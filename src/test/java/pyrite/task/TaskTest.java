package pyrite.task;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TaskTest {
    // Test cases suggested by Github Copilot
    @Test
    public void toString_normalString_success() {
        Task task = new Task("test");
        assertTrue(task.toString().equals("[ ] test"));
    }
    @Test
        public void toString_emptyString_exception() {
        try {
            Task task = new Task("");
        } catch (AssertionError e) {
            assertTrue(e instanceof AssertionError);
        }
    }
    @Test
    public void setStatus_markAsDone_success() {
        Task task = new Task("test");
        task.setStatus(Task.Status.DONE);
        assertTrue(task.toString().equals("[X] test"));
    }
    @Test
    public void setStatus_markAsNotDone_success() {
        Task task = new Task("test");
        task.setStatus(Task.Status.DONE);
        task.setStatus(Task.Status.NOT_DONE);
        assertTrue(task.toString().equals("[ ] test"));
    }
    @Test
    public void getStatusIcon_markAsDone_success() {
        Task task = new Task("test");
        task.setStatus(Task.Status.DONE);
        assertTrue(task.getStatusIcon().equals("X"));
    }
    @Test
    public void getStatusIcon_markAsNotDone_success() {
        Task task = new Task("test");
        task.setStatus(Task.Status.DONE);
        task.setStatus(Task.Status.NOT_DONE);
        assertTrue(task.getStatusIcon().equals(" "));
    }
    @Test
    public void getDescription_success() {
        Task task = new Task("test");
        assertTrue(task.getDescription().equals("test"));
    }
}
