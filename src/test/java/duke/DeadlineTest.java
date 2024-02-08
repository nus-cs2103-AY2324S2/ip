package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class DeadlineTest {
    @Test
    public void testDeadlineConstruction() {
        Deadline deadline = new Deadline("Submit report", false, "25/12/2023 1800");
        assertEquals("Submit report", deadline.description);
        assertEquals(false, deadline.isDone);
        assertEquals(LocalDateTime.of(2023, 12, 25, 18, 0), deadline.by);
    }

    @Test
    public void testToFileFormat() {
        Deadline deadline = new Deadline("Submit report", true, "25/12/2023 1800");
        String expectedFormat = "D | true | Submit report | 25/12/2023 1800";
        assertEquals(expectedFormat, deadline.toFileFormat());
    }

    @Test
    public void testToString() {
        Deadline deadline = new Deadline("Submit report", false, "25/12/2023 1800");
        // Adjust the expected string based on your specific toString implementation details
        String expectedString = "[D][ ] Submit report (by: Dec 25 2023, 6:00PM)";
        assertEquals(expectedString, deadline.toString());
    }

    @Test
    public void testDeadlineDateTimeParseException() {
        assertThrows(DateTimeParseException.class, () -> {
            new Deadline("Invalid date", false, "invalid-date-time");
        });
    }
}
