package duke.task;

import duke.exceptions.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskListTest {

    @Test
    public void testSetToDo() throws DukeException {
        String input = "Do ip";
        TaskList test = new TaskList();
        Todo todo = test.setToDo(input);
        assertEquals("Do ip", todo.getDescription());
    }
    @Test
    public void testEmptySpaces() {
        String input = "   ";
        TaskList test = new TaskList();
        assertThrows(DukeException.class, ()->test.setToDo(input));
    }

    @Test
    public void testSpacesAndWords() throws DukeException {
        String input = " project ";
        TaskList test = new TaskList();
        Todo todo = test.setToDo(input);
        assertEquals("project", todo.getDescription());
    }

}