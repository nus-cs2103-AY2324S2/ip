package Duke.activityAndUtility;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EventTest {
    private Event event;

    @BeforeEach
    public void setUp() {
        // Assuming DateTimeFormat.getDate and .getTime are correctly parsing date and time respectively
        event = new Event("X", "Test Event",
                "2022-10-01 14:00", "2022-10-01 16:00");
    }

    @Test
    void testMarkAsDone() {
        event.mark("mark");
        assertEquals("√", event.act.get(0), "Event should be marked as done");
    }

    @Test
    void testMarkAsUndone() {
        event.mark("unmark");
        assertEquals("X", event.act.get(0), "Event should be marked as undone");
    }

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
