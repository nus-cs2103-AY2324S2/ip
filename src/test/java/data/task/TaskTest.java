package data.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seiki.data.task.Task;

public class TaskTest {
    @Test
    public void toString_unmarkedTask() {
        Task task = new Task("test");
        assertEquals("test", task.toString());
    }

    @Test
    public void toString_markedTask() {
        Task task = new Task("test");
        task.markAsDone();
        assertEquals("test ✓", task.toString());
    }

    @Test
    public void toFile_unmarkedTask() {
        Task task = new Task("test");
        assertEquals("| 0 | test", task.toFile());
    }

    @Test
    public void toFile_markedTask() {
        Task task = new Task("test");
        task.markAsDone();
        assertEquals("| 1 | test", task.toFile());
    }
}
