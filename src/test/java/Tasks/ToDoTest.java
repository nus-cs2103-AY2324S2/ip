package Tasks;

import Exceptions.DukeException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ToDoTest {

    @Test
    void testToDoCreationPass() throws DukeException {
        ToDo todo = new ToDo("Buy groceries");
        assertEquals("[T][ ] Buy groceries", todo.toString());
        assertFalse(todo.isDone);
    }

    @Test
    void testToDoCreationFail() throws DukeException {
        assertThrows(DukeException.class, () -> new ToDo(""));
    }

    @Test
    void testMarkAsDone() throws DukeException {
        ToDo todo = new ToDo("Buy groceries");
        todo.setDone(true);
        assertEquals("[T][X] Buy groceries", todo.toString());
        assertTrue(todo.isDone);
    }

    @Test
    void testMarkAsUnDone() throws DukeException {
        ToDo todo = new ToDo("Buy groceries");
        todo.setDone(true);
        todo.setDone(false);
        assertEquals("[T][ ] Buy groceries", todo.toString());
        assertFalse(todo.isDone);
    }
}