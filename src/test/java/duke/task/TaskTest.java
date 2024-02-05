package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void testTaskConstructor() {
        Task task = new Task("Buy groceries");
        assertEquals("Buy groceries", task.toString());
        assertEquals(" ", task.getStatusIcon());
    }

    @Test
    public void testMarkAsDone() {
        Task task = new Task("Buy groceries");
        assertEquals(" ", task.getStatusIcon());
        task.markAsDone();
        assertEquals("X", task.getStatusIcon());
    }
}

