package talkingbot.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Class for testing the Event class.
 */
public class EventTest {
    /**
     * Tests the Event class' getSaveFileString() method.
     */
    @Test
    public void eventGetSaveFileStringTest() {
        assertEquals("E | 1 | meet mentor | 2024-01-30 1900 | 2024-01-30 2000",
                new Event("meet mentor", true, "2024-01-30 1900", "2024-01-30 2000").getSaveFileString());
    }

    /**
     * Tests the Event class' toString() method.
     */
    @Test
    public void eventToStringTest() {
        assertEquals("[E] [ ] technical interview (from: Feb 10 2024 1500 to: Feb 10 2024 1600)",
                new Event("technical interview", false, "2024-02-10 1500", "2024-02-10 1600").toString());
    }
}
