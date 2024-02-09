package duke.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
