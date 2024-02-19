package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void setDate_success() {
        Event event = new Event("name");
        event.setFromDate("2024-05-15 2100");
        assertEquals("May 15 2024 09:00 PM", event.getFromDate());
    }

    //CHECKSTYLE.OFF: EmptyCatchBlock

    @Test
    public void setDate_failure() {
        try {
            Event event = new Event("name");
            event.setFromDate("invalid");
            fail();
        } catch (DateTimeParseException e) {

        }

    }

    //CHECKSTYLE.ON: EmptyCatchBlock
}
