package seedu.chatteroo.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void testStringConversion() {
        ToDo todo = new ToDo("test");
        assertEquals("[T][ ] test", todo.toString());
    }

    @Test
    public void testMarkAsDone() {
        ToDo todo = new ToDo("test");
        todo.markAsDone();
        assertEquals("[T][X] test", todo.toString());
    }

    @Test
    public void testMarkAsNotDone() {
        ToDo todo = new ToDo("test");
        todo.markAsDone();
        todo.markAsNotDone();
        assertEquals("[T][ ] test", todo.toString());
    }

}
