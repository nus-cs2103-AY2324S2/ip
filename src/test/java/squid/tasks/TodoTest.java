package squid.tasks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import squid.constants.REGEX;

import static org.junit.Assert.assertEquals;

public class TodoTest {
    Todo task;
    @BeforeEach
    public void setup() {
        task = new Todo("testTest");
    }
    @Test
    public void testTodoIcon() {
        assertEquals("[T]", task.getType());
    }

    @Test
    public void testTodoName() {
        assertEquals("testTest", task.taskName);
    }

    @Test
    public void testDefaultUnmarked() {
        assertEquals(false, task.completed);
    }

    @Test
    public void testSetMarked() {
        task.setCompleted(true);
        assertEquals(true, task.completed);

        // After
        task.setCompleted(false);
    }

    @Test
    public void testString() {
        String expected = "[T][ ]: testTest";
        assertEquals(expected, task.toString());
    }

    @Test
    public void testParseString() {
        String expected = String.format("[T]%s-%stestTest\n", REGEX.TASK_SPLIT, REGEX.TASK_SPLIT);
        assertEquals(expected, task.parseStr());
    }

}
