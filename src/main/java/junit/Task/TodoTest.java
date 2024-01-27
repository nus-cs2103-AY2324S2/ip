package junit.Task;

import squid.tasks.Todo;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TodoTest {
    @Test
    public void testTodoIcon() {
        Todo task = new Todo("testTest");
        assertEquals("[T]", task.getType());
    }


}
