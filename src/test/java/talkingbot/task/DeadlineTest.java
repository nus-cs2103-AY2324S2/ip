package talkingbot.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class DeadlineTest {
    @Test
    public void deadlineGetSaveFileStringTest() {
        assertEquals("D | 0 | do readings | 2024-01-30 1930",
                new Deadline("do readings", false, "2024-01-30 1930").getSaveFileString());
    }

    @Test
    public void deadlineToStringTest() {
        assertEquals("[D] [X] finish essay (by: Jan 30 2024 2359)",
                new Deadline("finish essay", true, "2024-01-30 2359").toString());
    }
}
