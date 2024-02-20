package sleepy.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
public class EventTaskTest {
    @Test
    public void getDescription_normalEvent_success() {
        EventTask testEventTask = new EventTask("cs2103t lecture", "fri 1600", "fri 1800");
        assertEquals("[E][ ] cs2103t lecture (from: fri 1600 to: fri 1800)", testEventTask.getDescription());
    }
    @Test
    public void getDescription_dateChange_success() {
        EventTask testEventTask = new EventTask("nus semester", "2024-01-14", "2024-05-12");
        assertEquals("[E][ ] nus semester (from: 14 Jan 2024 to: 12 May 2024)",
                testEventTask.getDescription());
    }
}
