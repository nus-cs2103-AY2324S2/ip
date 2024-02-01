package taskList.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void testStringConversion() {
        ToDo event = new ToDo("read book");
        assertEquals("[T][ ] read book", event.toString());
    }
    
}
