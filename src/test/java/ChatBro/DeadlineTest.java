package ChatBro;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for the Deadline class toString() and toStorageFormat() methods.
 */
public class DeadlineTest {
    @Test
    public void testDeadlineToString() {
        Deadline deadline = new Deadline("Test!", "16-Jan-2001", true);
        assertEquals(deadline.toString(), "[D][X] Test! (by: 16-Jan-2001)");
    }
    @Test
    public void testDeadlineToStorageFormat() {
        Deadline deadline = new Deadline("Test!", "16-Jan-2001", true);
        assertEquals(deadline.toStorageFormat(), "D♢X♢Test!♢16-Jan-2001");
    }
}
