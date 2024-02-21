package pyrite.task;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

/**
 * Test cases for Event class.
 */
public class EventTest {
    // Test cases suggested by Github Copilot
    @Test
    public void toString_success() {
        Event event = new Event(
                "test",
                LocalDateTime.parse("2024-01-01T00:00"),
                LocalDateTime.parse("2024-01-01T00:00"));
        assertTrue(event.toString().equals("[E][ ] test (from: Jan 1 2024, 00:00 to: Jan 1 2024, 00:00)"));
    }
}
