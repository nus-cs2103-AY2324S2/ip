package pyrite.task;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

/**
 * Test cases for Deadline class.
 */
public class DeadlineTest {
    // Test cases suggested by Github Copilot
    /**
     * Test case for toString method.
     */
    @Test
    public void toString_success() {
        Deadline deadline = new Deadline("test", LocalDateTime.parse("2024-01-01T00:00"));
        assertTrue(deadline.toString().equals("[D][ ] test (by: Jan 1 2024, 00:00)"));
    }
}
