package tsundere.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void testTaskToString() {
        Task task = new Task("test");
        assertEquals("[ ] test", task.toString());
    }

    @Test
    public void testTaskToSaveString() {
        Task task = new Task("test");
        assertEquals("0,test", task.toSaveString());
    }

    @Test
    public void testTaskGetStatusIcon() {
        Task task = new Task("test");
        assertEquals(" ", task.getStatusIcon());
    }

    @Test
    public void testTaskMarkAsDone() {
        Task task = new Task("test");
        task.markAsDone();
        assertEquals("X", task.getStatusIcon());
    }

    @Test
    public void testTaskUnMark() {
        Task task = new Task("test");
        task.markAsDone();
        task.unMark();
        assertEquals(" ", task.getStatusIcon());
    }
}
