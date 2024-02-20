package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void createToDoTest() {
        ToDo task = new ToDo("study");
        assertEquals("[T][ ] study", task.toString());
    }

    @Test
    public void markToDoTest() {
        ToDo task = new ToDo("study");

        task.markDone();
        assertEquals("[T][X] study", task.toString());
    }

    @Test
    public void unmarkToDoTest() {
        ToDo task = new ToDo("study");
        task.markDone();
        task.markUndone();
        assertEquals("[T][ ] study", task.toString());
    }


    @Test
    public void storageToDoTest() {
        ToDo task = new ToDo("study");
        assertEquals("todo , 0 , study", task.toStorageString());

        task.markDone();
        assertEquals("todo , 1 , study", task.toStorageString());

        task.markUndone();
        assertEquals("todo , 0 , study", task.toStorageString());
    }
}
