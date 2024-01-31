package duke.task;

import duke.exception.DukeException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ToDoTest {

    @Test
    public void constructor_todoWithDescription_success() {
        try {
            ToDo todo = new ToDo("Test ToDo");
            assertEquals("Test ToDo", todo.getDescription());
            assertFalse(todo.checkStatus());
        } catch (DukeException e) {
            fail("Exception not expected: " + e.getMessage());
        }
    }

    @Test
    public void toString_todoCheckStringRepresentation_success() {
        try {
            ToDo todo = new ToDo("Test ToDo");
            assertEquals("Got it. I've added this task: \n [T][" + todo.getStatusIcon() + "] " + todo.getDescription(),
                    todo.toString());
        } catch (DukeException e) {
            fail("Exception not expected: " + e.getMessage());
        }
    }

}
