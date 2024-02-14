package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TodoTest {
    @Test
    public void initialise_withoutBoolean() {
        Task task = new Todo("test1");

        assertFalse(task.getIsDone());
        assertEquals("test1", task.getDescription());
        assertEquals(" ", task.getStatusIcon());
    }
    @Test
    public void initialise_withBoolean() {
        Task task = new Todo(true, "test2");

        assertTrue(task.getIsDone());
        assertEquals("X", task.getStatusIcon());
    }

    @Test
    public void mark() {
        Task task = new Todo("test3");
        task.markAsDone();

        assertTrue(task.getIsDone());
        assertEquals("X", task.getStatusIcon());
    }

    @Test
    public void markThenUnmark() {
        Task task = new Todo("test4");
        task.markAsDone();
        task.markAsNotDone();

        assertFalse(task.getIsDone());
        assertEquals(" ", task.getStatusIcon());
    }

    @Test
    public void toStringTest() {
        Task task = new Todo("test5");

        assertEquals("[T][ ] test5", task.toString());
    }
}