package seedu.klara.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {

    Task task = new Task("hello");

    @Test
    public void toString_StringConversion_success() {
        assertEquals("[ ] hello", new Task("hello").toString());
        Task task = new Task("hello");
        task.markAsDone();
        assertEquals("[X] hello", task.toString());
        task.markAsUndone();
        assertEquals("[ ] hello", task.toString());
    }

    @Test
    public void getDescription_success() {
        assertEquals("hello", new Task("hello").getDescription());
    }

    @Test
    public void isDone_success() {
        assertEquals(false, new Task("hello").isDone());
    }

    @Test
    public void markAsDone_success() {
        Task task = new Task("hello");
        task.markAsDone();
        assertEquals(true, task.isDone());
    }

    @Test
    public void markAsUndone_success() {
        Task task = new Task("hello");
        task.markAsDone();
        task.markAsUndone();
        assertEquals(false, task.isDone());
    }

    @Test
    public void containsSearchTerm_success() {
        Task task = new Task("hello");
        assertEquals(true, task.containsSearchTerm("hello"));
        assertEquals(false, task.containsSearchTerm("world"));
    }

}
