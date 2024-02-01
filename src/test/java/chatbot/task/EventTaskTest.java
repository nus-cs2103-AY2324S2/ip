package chatbot.task;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTaskTest {
    
    @Test
    public void checkInstantiation() {
        LocalDateTime start = LocalDateTime.parse("2021-08-21T18:00:00");
        LocalDateTime end = LocalDateTime.parse("2021-08-21T20:00:00");
        EventTask event = new EventTask("read book", start, end);
        String expectedString = "[E][ ] read book (from: Aug 21 2021 18:00 to: 20:00)";
        assertEquals(expectedString, event.toString());
        LocalDateTime end2 = LocalDateTime.parse("2021-08-22T20:00:00");
        EventTask event2 = new EventTask("read book", start, end2);
        String expectedString2 = "[E][ ] read book (from: Aug 21 2021 18:00 to: Aug 22 2021 20:00)";
        assertEquals(expectedString2, event2.toString());
    }

    @Test
    public void checkMark() {
        LocalDateTime start = LocalDateTime.parse("2021-08-21T18:00:00");
        LocalDateTime end = LocalDateTime.parse("2021-08-21T20:00:00");
        EventTask event = new EventTask("read book", start, end);
        event.mark();
        String expectedString = "[E][X] read book (from: Aug 21 2021 18:00 to: 20:00)";
        assertEquals(expectedString, event.toString());
    }

    @Test
    public void checkUnmark() {
        LocalDateTime start = LocalDateTime.parse("2021-08-21T18:00:00");
        LocalDateTime end = LocalDateTime.parse("2021-08-21T20:00:00");
        EventTask event = new EventTask("read book", start, end);
        event.mark();
        event.unmark();
        String expectedString = "[E][ ] read book (from: Aug 21 2021 18:00 to: 20:00)";
        assertEquals(expectedString, event.toString());
    }

    @Test
    public void checkExportToSave() {
        LocalDateTime start = LocalDateTime.parse("2021-08-21T18:00:00");
        LocalDateTime end = LocalDateTime.parse("2021-08-21T20:00:00");
        EventTask event = new EventTask("read book", start, end);
        String expectedString = "E,0,read book,2021-08-21T18:00,2021-08-21T20:00";
        assertEquals(expectedString, event.exportToSave());
    }

}
