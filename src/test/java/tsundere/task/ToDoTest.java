package tsundere.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void toDoToString_success() {
        ToDo todo = new ToDo("test");
        assertEquals("[T][ ] test", todo.toString());
    }

    @Test
    public void toDoToSaveString_success() {
        ToDo todo = new ToDo("test");
        assertEquals("T,0,test,", todo.toSaveString());
    }

    @Test
    public void toDoGetStatusIcon_success() {
        ToDo todo = new ToDo("test");
        assertEquals(" ", todo.getStatusIcon());
    }

    @Test
    public void toDoMarkAsDone_success() {
        ToDo todo = new ToDo("test");
        todo.markTaskAsDone();
        assertEquals("X", todo.getStatusIcon());
    }

    @Test
    public void toDoUnMark_success() {
        ToDo todo = new ToDo("test");
        todo.markTaskAsDone();
        assertEquals("X", todo.getStatusIcon());
        todo.unMarkTask();
        assertEquals(" ", todo.getStatusIcon());
    }
}
