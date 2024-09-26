package task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void toString_unMarkedDeadline_success() {
        Event event = new Event("project meeting", "31/8/2024 1400", "31/8/2024 1600");
        assertEquals("[E][ ] project meeting (from: 31 Aug 2024, 1400hrs to: 31 Aug 2024, 1600hrs)", event.toString());
    }

    @Test
    public void toString_markedDeadline_success() {
        Event event = new Event("project meeting", "31/8/2024 1400", "31/8/2024 1600");
        event.setDone(true);
        assertEquals("[E][X] project meeting (from: 31 Aug 2024, 1400hrs to: 31 Aug 2024, 1600hrs)", event.toString());
    }

    @Test
    public void toFileString_unMarkedDeadline_success() {
        Event event = new Event("project meeting", "31/8/2024 1400", "31/8/2024 1600");
        assertEquals("E|0|project meeting|31/8/2024 1400|31/8/2024 1600", event.toFileString());
    }

    @Test
    public void toFileString_markedDeadline_success() {
        Event event = new Event("project meeting", "31/8/2024 1400", "31/8/2024 1600");
        event.setDone(true);
        assertEquals("E|1|project meeting|31/8/2024 1400|31/8/2024 1600", event.toFileString());
    }
}
