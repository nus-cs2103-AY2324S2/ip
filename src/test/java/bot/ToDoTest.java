package bot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void testToString() {
        ToDo todo = new ToDo("Test ToDo");
        String expected = "T |   | Test ToDo";
        assertEquals(expected, todo.toString());
    }

    @Test
    public void testFromString() {
        String input = "T | X | Test ToDo";
        ToDo todo = ToDo.fromString(input);
        ToDo expected = new ToDo("Test ToDo");
        expected.markAsDone();
        assertEquals(expected.toString(), todo.toString());
    }
}