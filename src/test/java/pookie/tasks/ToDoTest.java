package pookie.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void testToString() {
        assertEquals("[T][ ]read book", new ToDo("read book").toString());
    }
}
