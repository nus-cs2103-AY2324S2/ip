package data.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seiki.data.task.ToDoTask;

public class ToDoTaskTest {

    @Test
    public void toString_unmarkedToDo() {
        ToDoTask task = new ToDoTask("test");
        assertEquals("[T] test", task.toString());
    }

    @Test
    public void toString_markedToDo() {
        ToDoTask task = new ToDoTask("test");
        task.markAsDone();
        assertEquals("[T] test ✓", task.toString());
    }

    @Test
    public void toFile_unmarkedToDo() {
        ToDoTask task = new ToDoTask("test");
        assertEquals("T | 0 | test", task.toFile());
    }

    @Test
    public void toFile_markedToDo() {
        ToDoTask task = new ToDoTask("test");
        task.markAsDone();
        assertEquals("T | 1 | test", task.toFile());
    }
}
