package Duke;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * JUnit test for DukeException class.
 */
public class DukeExceptionTest {

    /**
     * Tests the DukeException message.
     */
    @Test
    public void testDukeExceptionMessage() {
        DukeException dukeException = new DukeException("Test message");
        assertEquals("Test message", dukeException.getMessage());
    }
}
