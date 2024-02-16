package raphael.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
public class TaskTest {
    @Test
    public void check_doneTask() {
        assertEquals(-1, new Task("stub", true).markTaskAsDone());
    }
    @Test
    public void check_undoneTask() {
        assertEquals(0, new Task("stub", false).markTaskAsDone());
    }
    @Test
    public void uncheck_doneTask() {
        assertEquals(0, new Task("stub", true).markTaskAsUndone());
    }
    @Test
    public void uncheck_undoneTask() {
        assertEquals(-1, new Task("stub", false).markTaskAsUndone());
    }
    @Test
    public void isContaining_positiveResult() {
        assertTrue(new Task("stub", true).isContaining("stub"));
    }
    @Test
    public void isContaining_negativeResult() {
        assertFalse(new Task("stub", true).isContaining("book"));
    }
    @Test
    public void toFileFormat_doneTask() {
        assertEquals("1 |&| stub", new Task("stub", true).toFileFormat());
    }
    @Test
    public void toFileFormat_undoneTask() {
        assertEquals("0 |&| stub", new Task("stub", false).toFileFormat());
    }
    @Test
    public void toString_doneTask() {
        assertEquals("[X] stub", new Task("stub", true).toString());
    }
    @Test
    public void toString_undoneTask() {
        assertEquals("[ ] stub", new Task("stub", false).toString());
    }
}
