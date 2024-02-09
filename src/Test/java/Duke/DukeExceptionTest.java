package Duke;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeExceptionTest {

    @Test
    public void testDukeExceptionMessage() {
        DukeException dukeException = new DukeException("Test message");
        assertEquals("Test message", dukeException.getMessage());
    }
}
