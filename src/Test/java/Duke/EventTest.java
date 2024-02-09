package Duke;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testEventToString() {
        Event event = new Event("Description", "From", "To");
        assertEquals("[E][ ] Description (from: From to: To)", event.toString());
    }

    @Test
    public void testEventToSaveString() {
        Event event = new Event("Description", "From", "To");
        assertEquals("E | 0 | Description | From - To", event.toSaveString());
    }

    @Test
    public void testEvent() {
        Event event = new Event("Meeting", "2024-02-08 10:00", "2024-02-08 12:00");
        assertEquals("[E][ ] Meeting (from: 2024-02-08 10:00 to: 2024-02-08 12:00)", event.toString());
    }
}
