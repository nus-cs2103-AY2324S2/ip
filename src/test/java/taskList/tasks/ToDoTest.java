package taskList.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import tasklist.tasks.ToDo;

public class ToDoTest {
    @Test
    public void testStringConversion() {
        ToDo event = new ToDo("read book");
        assertEquals("[T][ ] read book", event.toString());
    }
}
