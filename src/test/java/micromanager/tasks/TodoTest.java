package micromanager.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    public void testUnmarkedFileStringConversion() {
        Todo testTodo = new Todo("task");
        assertEquals("[T][ ] task", testTodo.toString());
    }

    @Test
    public void testMarkedFileStringConversion() {
        Todo testTodo = new Todo("task");
        testTodo.markDone();
        assertEquals("[T][X] task", testTodo.toString());
    }
}
