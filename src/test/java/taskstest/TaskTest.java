package taskstest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import tasks.Task;
public class TaskTest {

    @Test
    public void testTaskInit() {
        Task task = new Task("hello");
        assertEquals("hello", task.getDescription());
        assertEquals(false, task.getStatus());
    }
    @Test
    public void testStatusIcon() {
        Task task = new Task("hello");
        assertEquals(task.getStatusIcon(), "[ ]");
        task.markDone(true);
        assertEquals(task.getStatusIcon(), "[X]");
    }

    public static void main(String[] args) {
        TaskTest test = new TaskTest();
        test.testTaskInit();
        test.testStatusIcon();
    }
}
