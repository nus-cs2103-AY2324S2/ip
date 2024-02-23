package hal.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskTest {
    private static class ConcreteTask extends Task {
        ConcreteTask(String taskName, boolean done) {
            super(taskName, done);
        }
    }
    private Task task;

    @BeforeEach
    public void setUp() {
        task = new ConcreteTask("Test Task", false);
    }

    @Test
    public void testConstructor() {
        assertEquals("Test Task", task.getTaskName());
        assertFalse(task.getDone());
    }

    @Test
    public void testMarkDone() {
        task.markDone();
        assertTrue(task.getDone());
    }

    @Test
    public void testMarkUndone() {
        task.markDone();
        task.markUndone();
        assertFalse(task.getDone());
    }

    @Test
    public void testToString() {
        assertEquals("[ ] [  ] Test Task", task.toString());
        task.markDone();
        assertEquals("[ ] [X] Test Task", task.toString());
    }

    @Test
    public void testEncode() {
        String[] encoded = task.encode();
        assertEquals(3, encoded.length);
        assertEquals(" ", encoded[0]);
        assertEquals("false", encoded[1]);
        assertEquals("Test Task", encoded[2]);
    }
}
