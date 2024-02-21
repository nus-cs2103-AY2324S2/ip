package seedu.chatteroo.tasks;

import org.junit.jupiter.api.Test;
import seedu.chatteroo.ChatterooException;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class EventTest {
    @Test
    public void testStringConversion() throws ChatterooException {
        Event event = new Event("test", "2021-08-25 1800", "2021-08-25 2100");
        assertEquals("[E][ ] test (from: Aug 25 2021, 18:00 to: Aug 25 2021, 21:00)", event.toString());
    }

    @Test
    public void testMarkAsDone() throws ChatterooException {
        Event event = new Event("test", "2021-08-25 1800", "2021-08-25 2100");
        event.markAsDone();
        assertEquals("[E][X] test (from: Aug 25 2021, 18:00 to: Aug 25 2021, 21:00)", event.toString());
    }
}
