package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    @Test
    public void testToString() {
        ToDo todo = new ToDo("Buy groceries");
        String result = todo.toString();
        assertEquals("[T][ ] Buy groceries", result);
    }
    @Test
    public void testGetType() {
        ToDo todo = new ToDo("Read a book");
        String result = todo.getType();
        assertEquals("T", result);
    }

}

