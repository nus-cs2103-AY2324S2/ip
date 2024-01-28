package tsundere.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testEventToString() {
        Event event = new Event("test ", "1 ", "2");
        assertEquals("[E][ ] test (from: 1 to: 2)", event.toString());
    }

    @Test
    public void testEventToSaveString() {
        Event event = new Event("test ", "1 ", "2");
        assertEquals("E,0,test ,1 ,2", event.toSaveString());
    }

    @Test
    public void testEventGetStatusIcon() {
        Event event = new Event("test ", "1 ", "2");
        assertEquals(" ", event.getStatusIcon());
    }

    @Test
    public void testEventMarkAsDone() {
        Event event = new Event("test ", "1 ", "2");
        event.markAsDone();
        assertEquals("X", event.getStatusIcon());
    }

    @Test
    public void testEventUnMark() {
        Event event = new Event("test ", "1 ", "2");
        event.markAsDone();
        event.unMark();
        assertEquals(" ", event.getStatusIcon());
    }
}
