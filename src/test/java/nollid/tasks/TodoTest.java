package nollid.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * A set of tests for the Todo class.
 */
public class TodoTest {
    /**
     * Tests the creation of a Todo instance with a provided description.
     * Verifies that the toString() method returns the expected result.
     */
    @Test
    public void createTodo_descriptionProvided_success() {
        Todo todo = new Todo("Buy groceries");

        String result = todo.toString();

        assertEquals("[T][ ] Buy groceries", result);
    }

    /**
     * Tests marking a Todo instance as done.
     * Verifies that the setDone() method correctly updates the Todo's status.
     */
    @Test
    public void setDone_markAsDone_success() {
        Todo todo = new Todo("Read a book");

        todo.setDone(true);

        assertEquals("[T][X] Read a book", todo.toString());
    }
}

