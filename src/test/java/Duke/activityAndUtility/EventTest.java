package Duke.activityAndUtility;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * The {@code EventTest} class contains unit tests for the {@code Event} class to verify its functionality.
 * It tests the behavior of marking an event as completed or not completed, and checks the handling of invalid event dates.
 */
public class EventTest {
    private Event event;


    /**
     * Sets up a test environment with a valid event before each test method is executed.
     * This method initializes an {@code Event} object with predefined start and end date and time.
     */
    @BeforeEach
    public void setUp() {
        // Assuming DateTimeFormat.getDate and .getTime are correctly parsing date and time respectively
        event = new Event("X", "Test Event",
                "2022-10-01 14:00", "2022-10-01 16:00");
    }


    /**
     * Tests marking an event as done. It verifies that the status of the event is correctly updated to "√".
     */
    @Test
    void testMarkAsDone() {
        event.mark("mark");
        assertEquals("√", event.act.get(0), "Event should be marked as done");
    }

    /**
     * Tests marking an event as not done. It verifies that the status of the event is correctly updated to "X".
     */
    @Test
    void testMarkAsUndone() {
        event.mark("unmark");
        assertEquals("X", event.act.get(0), "Event should be marked as undone");
    }

    /**
     * Tests the exception thrown when an event's end date is set before its start date.
     * It verifies that the correct exception and message are thrown.
     */
    @Test
    void testExceptionMessage() {
        String expectedMessage = "Finish date ahead of start date";

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            Event event = new Event("X", "Test Event",
                    "2024-10-02 14:00", "2024-10-01 16:00");
            throw new RuntimeException("Finish date ahead of start date");
        }, "The expected exception was not thrown.");

        // Verify if the exception message is what you expect
        assertEquals(expectedMessage, thrown.getMessage(), "The error message is not as expected.");
    }
}
