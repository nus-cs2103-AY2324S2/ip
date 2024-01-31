package Duke.Task.Events;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventsTest {
    @Test
    public void testToString() {
        Events events = new Events("Project meeting", "Mon 2pm", "6pm");
        events.markAsDone();
        assertEquals("  [E][X] Project meeting (from: Mon 2pm to: 6pm)", events.toString());
    }

    @Test
    public void testToFile() {
        Events events = new Events("Project meeting", "25 Jan 2pm", "6pm");
        events.markAsDone();
        assertEquals("E|1|Project meeting|25 Jan 2pm|6pm", events.toFile());
    }
}
