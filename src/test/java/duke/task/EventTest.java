package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;


public class EventTest {
    @Test
    public void getEventFromForDisplay_validFormat_returnsFormattedDateTime() {
        String content = "Project";
        String from = "2022-12-31 12:31";
        String to = "2023-01-02 03:04";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime parsedFromDateTime = LocalDateTime.parse(from, formatter);
        LocalDateTime parsedToDateTime = LocalDateTime.parse(to, formatter);
        Event newTask = new Event(content, parsedFromDateTime, parsedToDateTime);
        assertEquals("DECEMBER 31 2022 12:31PM", newTask.getEventFromForDisplay());
    }

    @Test
    public void getEventToForDisplay_validFormat_returnsFormattedDateTime() {
        String content = "Project";
        String from = "2022-12-31 12:31";
        String to = "2023-01-02 03:04";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime parsedFromDateTime = LocalDateTime.parse(from, formatter);
        LocalDateTime parsedToDateTime = LocalDateTime.parse(to, formatter);
        Event newTask = new Event(content, parsedFromDateTime, parsedToDateTime);
        assertEquals("JANUARY 02 2023 03:04AM", newTask.getEventToForDisplay());
    }
}
