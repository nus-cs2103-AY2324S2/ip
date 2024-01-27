package squid.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class TodoTest {
    @Test
    public void testTodoIcon() {
        Todo task = new Todo("testTest");
        assertEquals("[T]", task.getType());
    }

    @Test public void testTodoName() {
        Todo task = new Todo("testTest");
        assertEquals("testTest", task.task);
    }
}
