package sleepy.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class EventTest {

    @Test
    public void getDescription_normalEvent_success() {
        Event testEvent = new Event("event cs2103t lecture /from fri 1600 /to fri 1800",
                "cs2103t lecture", "fri 1600", "fri 1800");
        assertEquals("[E][ ] cs2103t lecture (from: fri 1600 to: fri 1800)", testEvent.getDescription());
    }

    @Test
    public void getDescription_dateChange_success() {
        Event testEvent = new Event("event nus semester /from 2024-01-14 /to 2024-05-12",
                "nus semester", "2024-01-14", "2024-05-12");
        assertEquals("[E][ ] nus semester (from: 14 Jan 2024 to: 12 May 2024)", testEvent.getDescription());
    }
}
