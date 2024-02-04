package ben.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class EventTest {

    @Test
    public void testSaveTask() {
        LocalDate startDate = LocalDate.parse("2024-02-10");
        LocalDate endDate = LocalDate.parse("2024-02-12");
        Event event = new Event(false, "Conference", startDate, endDate);
        assertEquals("E | 0 | Conference | 2024-02-10 | 2024-02-12", event.saveTask());
    }

    @Test
    public void testToString() {
        LocalDate startDate = LocalDate.parse("2024-02-15");
        LocalDate endDate = LocalDate.parse("2024-02-17");
        Event event = new Event(false, "Vacation", startDate, endDate);
        assertEquals("[E][ ] Vacation (from: Feb 15 2024 to: Feb 17 2024)", event.toString());
    }

    @Test
    public void testSaveTaskCompletedEvent() {
        LocalDate startDate = LocalDate.parse("2024-03-01");
        LocalDate endDate = LocalDate.parse("2024-03-03");
        Event event = new Event(true, "Team building", startDate, endDate);
        assertEquals("E | 1 | Team building | 2024-03-01 | 2024-03-03", event.saveTask());
    }

    @Test
    public void testToStringCompletedEvent() {
        LocalDate startDate = LocalDate.parse("2024-03-05");
        LocalDate endDate = LocalDate.parse("2024-03-07");
        Event event = new Event(true, "Workshop", startDate, endDate);
        assertEquals("[E][X] Workshop (from: Mar 5 2024 to: Mar 7 2024)", event.toString());
    }

    @Test
    public void testInvalidStartDate() {
        Throwable exception = assertThrows(DateTimeParseException.class, () -> {
            new Event(false, "Invalid date", LocalDate.parse("InvalidStartDate"), LocalDate.parse("2024-02-12"));
        });

        assertEquals("Text 'InvalidStartDate' could not be parsed at index 0", exception.getMessage());
    }

    @Test
    public void testInvalidEndDate() {
        Throwable exception = assertThrows(DateTimeParseException.class, () -> {
            new Event(false, "Invalid date", LocalDate.parse("2024-02-10"), LocalDate.parse("InvalidEndDate"));
        });

        assertEquals("Text 'InvalidEndDate' could not be parsed at index 0", exception.getMessage());
    }
}
