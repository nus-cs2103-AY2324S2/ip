package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EventTest {
    private Event event;

    @BeforeEach
    public void setUp() throws DukeException {
        event = new Event("project meeting", "2023-02-01", "2023-02-03");
    }

    @Test
    public void eventCreation_validDetails_correctStringRepresentation() {
        assertEquals("[E][ ] project meeting (from: Feb 01 2023 to: Feb 03 2023)", event.toString());
    }

    @Test
    public void eventCreation_invalidDateFormat_throwsException() {
        assertThrows(DukeException.class, () -> new Event("project meeting", "3rd August", "4pm"));
    }

    @Test
    public void markAsDone_eventNotDoneBefore_markedAsDone() {
        event.markAsDone();
        assertEquals("[E][X] project meeting (from: Feb 01 2023 to: Feb 03 2023)", event.toString());
    }

    @Test
    public void markAsUndone_eventInitiallyDone_markedAsNotDone() {
        event.markAsDone();
        event.unmark();
        assertEquals("[E][ ] project meeting (from: Feb 01 2023 to: Feb 03 2023)", event.toString());
    }
}
