package data.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seiki.data.task.ToDo;

public class ToDoTest {

    @Test
    public void toString_unmarkedToDo() {
        ToDo task = new ToDo("test");
        assertEquals("[T] test", task.toString());
    }

    @Test
    public void toString_markedToDo() {
        ToDo task = new ToDo("test");
        task.markAsDone();
        assertEquals("[T] test ✓", task.toString());
    }

    @Test
    public void toFile_unmarkedToDo() {
        ToDo task = new ToDo("test");
        assertEquals("T | 0 | test", task.toFile());
    }

    @Test
    public void toFile_markedToDo() {
        ToDo task = new ToDo("test");
        task.markAsDone();
        assertEquals("T | 1 | test", task.toFile());
    }
}
