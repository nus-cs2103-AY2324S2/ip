package felix.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testToFileStringMethod(){
        Event event = new Event("project meeting",
               "2024-01-10 1400", "2024-01-10 1700");
        event.markAsDone();
        assertEquals("E | X | project meeting | 2024-01-10 1400 | 2024-01-10 1700",
                event.toFileString());
    }

    @Test
    public void testToStringMethod(){
        Event event = new Event("project meeting",
                "2024-01-10 1400", "2024-01-10 1700");
        event.markAsDone();
        assertEquals("[E][X] project meeting (from: 10 Jan 2024 1400 to: 10 Jan 2024 1700)",
                event.toString());
    }

}
