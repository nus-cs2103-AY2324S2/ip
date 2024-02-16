package numerator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import numerator.task.Task;
import numerator.task.ToDo;

public class ToDoTest {
    @Test
    public void toString_isNotDone_success() {
        Task todo = new ToDo("read book");
        String expected = "[T][ ] read book";
        assertEquals(expected, todo.toString());
    }

    @Test
    public void toString_isDone_success() {
        Task todo = new ToDo("read book");
        todo.markAsDone();
        String expected = "[T][X] read book";
        assertEquals(expected, todo.toString());
    }

    @Test
    public void getSaveString_isNotDone_success() {
        Task todo = new ToDo("read book");
        String expected = "T | 0 | read book |  ";
        assertEquals(expected, todo.getSaveString());
    }

    @Test
    public void getSaveString_isDone_success() {
        Task todo = new ToDo("read book");
        todo.markAsDone();
        String expected = "T | 1 | read book |  ";
        assertEquals(expected, todo.getSaveString());
    }
}
