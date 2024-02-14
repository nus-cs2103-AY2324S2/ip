package tasklist.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void testStringConversion() {
        ToDo event = new ToDo("read book");
        assertEquals("[T][ ] read book", event.toString());
    }
}
