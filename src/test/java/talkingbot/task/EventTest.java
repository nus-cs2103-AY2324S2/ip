package talkingbot.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void eventGetSaveFileStringTest() {
        assertEquals("E | 1 | meet mentor | 2024-01-30 1900 | 2024-01-30 2000",
                new Event("meet mentor", true, "2024-01-30 1900", "2024-01-30 2000").getSaveFileString());
    }

    @Test
    public void eventToStringTest() {
        assertEquals("[E] [ ] technical interview (from: Feb 10 2024 1500 to: Feb 10 2024 1600)",
                new Event("technical interview", false, "2024-02-10 1500", "2024-02-10 1600").toString());
    }
}
