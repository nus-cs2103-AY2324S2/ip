package seedu.duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import exception.DukeException;
import task.Event;

public class EventTest {

    @Test
    public void testEventConstructor_ToDates() {
        try {
            Event event = new Event("Birthday party", "12/02/2022 1800", "12/02/2022 2000");
            assertEquals("Birthday party", event.getName());
            assertEquals("2022-02-12T20:00", event.getTo().toString());
        } catch (DukeException e) {
        }
    }

    @Test
    public void testEventConstructor_FromDates() {
        try {
            Event event = new Event("Birthday party", "12/02/2022 1800", "12/02/2022 2000");
            assertEquals("Birthday party", event.getName());
            assertEquals("2022-02-12T18:00", event.getFrom().toString());
        } catch (DukeException e) {
        }
    }
}
