package thecount.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {

    @Test
    public void testGetType() {
        ToDo todo = new ToDo("CS2103T iP");

        assertEquals("T", todo.getType());
    }

    @Test
    public void testToString() {
        ToDo todo = new ToDo("CS2103T iP");

        assertEquals("[T][ ] CS2103T iP", todo.toString());
    }

}
