package atlas.task;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void createEvent_ShouldHaveCorrectToString() {
        LocalDateTime startDate = LocalDateTime.of(2021, 11, 30, 00, 00);
        LocalDateTime dueDate = LocalDateTime.of(2021, 12, 31, 23, 59);
        DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
        DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
        Event ev = new Event("Festival", startDate, dueDate);
        assertEquals("[E][ ] Festival (from: " + startDate.format(FORMATTER) + " to: " + dueDate.format(FORMATTER) +")", ev.toString());
    }
    @Test
    public void toggle_ShouldHaveCorrectToString() {
        LocalDateTime startDate = LocalDateTime.of(2021, 11, 30, 00, 00);
        LocalDateTime dueDate = LocalDateTime.of(2021, 12, 31, 23, 59);
        DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
        DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
        Event ev = new Event("Festival", startDate, dueDate);
        ev.toggle();
        assertEquals("[E][X] Festival (from: " + startDate.format(FORMATTER) + " to: " + dueDate.format(FORMATTER) +")", ev.toString());
    }

}
