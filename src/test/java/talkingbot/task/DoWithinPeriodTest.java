package talkingbot.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Class for testing the DoWithinPeriod class.
 */
public class DoWithinPeriodTest {
    /**
     * Tests the DoWithinPeriod class' getSaveFileString() method.
     */
    @Test
    public void doWithinPeriodGetSaveFileStringTest() {
        assertEquals("W | 0 | do readings | 2024-01-30 1930 | 2024-01-30 2030",
                new DoWithinPeriod("do readings", false, "2024-01-30 1930", "2024-01-30 2030").getSaveFileString());
    }

    /**
     * Tests the DoWithinPeriod class' toString() method.
     */
    @Test
    public void doWithinPeriodToStringTest() {
        assertEquals("[W] [X] do essay (between Feb 10 2024 1800 and Feb 10 2024 2000)",
                new DoWithinPeriod("do essay", true, "2024-02-10 1800", "2024-02-10 2000").toString());
    }
}
