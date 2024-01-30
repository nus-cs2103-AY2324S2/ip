package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    void testToString() {
        Event event = new Event("concert", "2023-01-01", "2023-01-02");
        assertEquals("[ ] Event : concert [ Jan 01 2023 - Jan 02 2023 ]", event.toString());
    }
}