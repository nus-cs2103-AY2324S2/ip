package ghbot.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * DeadlineTest Class.
 */
public class DeadlineTest {
    @Test
    public void testUnmarkToFile() {
        Deadline deadline = new Deadline("homework", "21 Jan 2023 1000AM");
        assertEquals("D | 0 | homework | 21 Jan 2023 1000AM", deadline.toFile());
    }

    @Test
    public void testMarkToFile() {
        Deadline deadline = new Deadline("homework", "21 Jan 2023 1000AM");
        deadline.isCompleted();
        assertEquals("D | 1 | homework | 21 Jan 2023 1000AM", deadline.toFile());
    }

    @Test
    public void testUnmarkToString() {
        Deadline deadline = new Deadline("homework", "21 Jan 2023 1000AM");
        assertEquals("[D][ ] homework (by: 21 Jan 2023 1000AM)", deadline.toString());
    }

    @Test
    public void testMarkToString() {
        Deadline deadline = new Deadline("homework", "21 Jan 2023 1000AM");
        deadline.isCompleted();
        assertEquals("[D][X] homework (by: 21 Jan 2023 1000AM)", deadline.toString());
    }
}
