package tasks;

import org.junit.jupiter.api.Test;

import exceptions.KewgyException;
import tasks.ToDo;

import static org.junit.jupiter.api.Assertions.*;

class ToDoTest {

    @Test
    void testToDoCreationPass() throws KewgyException {
        ToDo todo = new ToDo("Buy groceries");
        assertEquals("[T][ ] Buy groceries", todo.toString());
        assertFalse(todo.isDone);
    }

    @Test
    void testToDoCreationFail() throws KewgyException {
        assertThrows(KewgyException.class, () -> new ToDo(""));
    }

    @Test
    void testMarkAsDone() throws KewgyException {
        ToDo todo = new ToDo("Buy groceries");
        todo.setDone(true);
        assertEquals("[T][X] Buy groceries", todo.toString());
        assertTrue(todo.isDone);
    }

    @Test
    void testMarkAsUnDone() throws KewgyException {
        ToDo todo = new ToDo("Buy groceries");
        todo.setDone(true);
        todo.setDone(false);
        assertEquals("[T][ ] Buy groceries", todo.toString());
        assertFalse(todo.isDone);
    }
}