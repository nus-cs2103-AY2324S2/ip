package pookie.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void testToString() {
        assertEquals("[T][ ]read book", new ToDo("read book").toString());
    }

    @Test
    public void testWriteToFileString() {
        assertEquals("todo read book", new ToDo("read book").writeToFileString());
    }

    @Test
    public void testToDo() {
        ToDo todo = new ToDo("read book");
        assertEquals("[T][ ]read book", todo.toString());
    }
}
