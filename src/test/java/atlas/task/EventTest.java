package atlas.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    private final static int DEFAULT_PRIORITY = 3;

    @Test
    public void createEventShouldHaveCorrectToString() {
        LocalDateTime startDate = LocalDateTime.of(2021, 11, 30, 00, 00);
        LocalDateTime dueDate = LocalDateTime.of(2021, 12, 31, 23, 59);
        DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
        DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
        Event ev = new Event("Festival", startDate, dueDate, DEFAULT_PRIORITY);
        assertEquals(ev.toString(), "[E][ ] Festival (P:" + DEFAULT_PRIORITY + ") "
                + "(from: " + startDate.format(FORMATTER) + " to: " + dueDate.format(FORMATTER) + ")");
    }

    @Test
    public void toggleShouldHaveCorrectToString() {
        LocalDateTime startDate = LocalDateTime.of(2021, 11, 30, 00, 00);
        LocalDateTime dueDate = LocalDateTime.of(2021, 12, 31, 23, 59);
        DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
        DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
        Event ev = new Event("Festival", startDate, dueDate, DEFAULT_PRIORITY);
        ev.toggle();
        assertEquals(ev.toString(), "[E][X] Festival (P:" + DEFAULT_PRIORITY + ") "
                + "(from: " + startDate.format(FORMATTER) + " to: " + dueDate.format(FORMATTER) + ")");
    }

}
