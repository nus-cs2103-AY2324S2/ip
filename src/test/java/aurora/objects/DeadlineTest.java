package aurora.objects;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class DeadlineTest {
    LocalDateTime testDate = LocalDateTime.of(2022, 10, 10, 10, 0);
    Deadline deadline = new Deadline("Test Deadline", testDate);

    @Test
    void constructorTest() {
        assertFalse(deadline.getStatus());
    }

    @Test
    void getDateTest() {
        assertEquals(testDate, deadline.getDate());
    }

    @Test
    void toFileStringTest() {
        String expected = "D | 0 | Test Deadline | Oct 10 2022, 10:00";
        assertEquals(expected, deadline.toFileString());
    }

    @Test
    void toStringTest() {
        String expected = "[D][ ] Test Deadline (by: Oct 10 2022, 10:00)";
        assertEquals(expected, deadline.toString());
    }
}
