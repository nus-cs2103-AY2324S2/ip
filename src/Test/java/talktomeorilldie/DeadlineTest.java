package talktomeorilldie;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

/**
 * JUnit test for Deadline class.
 */
public class DeadlineTest {

    /**
     * Tests the toString method of Deadline.
     */
    @Test
    public void testGetDate() {
        LocalDateTime dateTime = LocalDateTime.of(2024, 2, 8, 12, 0);
        Deadline deadline = new Deadline("Test Deadline", dateTime);
        assertEquals(dateTime, deadline.getDate());
    }
}
