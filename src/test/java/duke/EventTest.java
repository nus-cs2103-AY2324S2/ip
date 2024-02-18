package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void toString_success() {
        Event Cook = new Event("Cook", "E", "10-02-2001 12:12", "11-02-2001 12:12");
        assertEquals("[E][] Cook (From: 10-02-2001 12:12 To: 11-02-2001 12:12)", Cook.toString());
    }

    @Test
    public void saveString_mark_success() {
        Event Cook = new Event("Cook", "E", "10-02-2001 12:12", "11-02-2001 12:12");
        Cook.mark();
        assertEquals("E|1|Cook|10-02-2001 12:12|11-02-2001 12:12", Cook.saveString());
    }
}
