package riri;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void eventCreationTest1() {
        Event event = new Event("party", "2/2/2024", "3/2/2024");
        assertEquals(event.toString(), "[E][ ] party (from: Feb 2 2024 to Mar 2 2024)");
    }
}
