package hal.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class DeadlineTest {
    private Deadline deadline;

    @BeforeEach
    public void setUp() {
        LocalDateTime dateTime = LocalDateTime.of(2024, 1, 31, 12, 0);
        deadline = new Deadline("Finish Assignment", dateTime, false);
    }

    @Test
    public void testConstructor() {
        assertEquals("Finish Assignment", deadline.getTaskName());
        assertFalse(deadline.getDone());
        assertEquals("D", deadline.getIdentifier());
        LocalDateTime expectedDateTime = LocalDateTime.of(2024, 1, 31, 12, 0);
        assertEquals(expectedDateTime, deadline.getBy());
    }

    @Test
    public void testToString() {
        assertEquals("[D] [  ] Finish Assignment by: 31-01-2024 12:00", deadline.toString());
    }

    @Test
    public void testEncode() {
        String[] encoded = deadline.encode();
        assertEquals(4, encoded.length);
        assertEquals("D", encoded[0]);
        assertEquals("false", encoded[1]);
        assertEquals("Finish Assignment", encoded[2]);
        assertEquals("31-01-2024 12:00", encoded[3]);
    }
}
