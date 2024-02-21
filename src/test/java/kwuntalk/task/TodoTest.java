package kwuntalk.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void testFormatTask() {
        Todo t1 = new Todo("test 1");
        assertEquals("T | 0 | test 1", t1.formatTask());

        Todo t2 = new Todo("test 2");
        t2.changeMark("MARK");
        assertEquals("T | 1 | test 2", t2.formatTask());
    }

    @Test
    public void testToString() {
        Todo t1 = new Todo("test 1");
        assertEquals("[T][ ] test 1", t1.toString());

        Todo t2 = new Todo("test 2");
        t2.changeMark("MARK");
        assertEquals("[T][X] test 2", t2.toString());
    }
}
