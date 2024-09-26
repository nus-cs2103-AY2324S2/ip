package tsundere.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void eventToString_success() {
        Event event = new Event("test", "1", "2");
        assertEquals("[E][ ] test (from: 1 to: 2)", event.toString());
    }

    @Test
    public void eventToSaveString_success() {
        Event event = new Event("test", "1", "2");
        assertEquals("E,0,0,test,1,2,", event.toSaveString());
    }

    @Test
    public void eventGetStatusIcon_success() {
        Event event = new Event("test", "1", "2");
        assertEquals(" ", event.getStatusIcon());
    }

    @Test
    public void eventMarkAsDone_success() {
        Event event = new Event("test", "1", "2");
        event.markTaskAsDone();
        assertEquals("X", event.getStatusIcon());
    }

    @Test
    public void eventUnMark_success() {
        Event event = new Event("test", "1", "2");
        event.markTaskAsDone();
        assertEquals("X", event.getStatusIcon());
        event.unMarkTask();
        assertEquals(" ", event.getStatusIcon());
    }
}
