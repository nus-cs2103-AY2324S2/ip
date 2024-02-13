package Duke;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void testGetBy() {
        LocalDateTime dateTime = LocalDateTime.of(2024, 2, 8, 12, 0);
        Deadline deadline = new Deadline("Test Deadline", dateTime);
        assertEquals(dateTime, deadline.getBy());
    }

    @Test
    public void testToString() {
        LocalDateTime dateTime = LocalDateTime.of(2024, 2, 8, 12, 0);
        Deadline deadline = new Deadline("Test Deadline", dateTime);
        assertEquals("[D][ ] Test Deadline (by: Feb 8 2024, 12:00PM)", deadline.toString());
    }
}
