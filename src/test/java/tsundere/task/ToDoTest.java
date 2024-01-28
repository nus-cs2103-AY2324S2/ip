package tsundere.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void testToDoToString() {
        ToDo todo = new ToDo("test");
        assertEquals("[T][ ] test", todo.toString());
    }

    @Test
    public void testToDoToSaveString() {
        ToDo todo = new ToDo("test");
        assertEquals("T,0,test", todo.toSaveString());
    }

    @Test
    public void testToDoGetStatusIcon() {
        ToDo todo = new ToDo("test");
        assertEquals(" ", todo.getStatusIcon());
    }

    @Test
    public void testToDoMarkAsDone() {
        ToDo todo = new ToDo("test");
        todo.markAsDone();
        assertEquals("X", todo.getStatusIcon());
    }

    @Test
    public void testToDoUnMark() {
        ToDo todo = new ToDo("test");
        todo.markAsDone();
        todo.unMark();
        assertEquals(" ", todo.getStatusIcon());
    }
}
