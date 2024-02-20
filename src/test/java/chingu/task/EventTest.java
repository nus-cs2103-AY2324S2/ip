package chingu.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void toStringTest() {
        Event testEvent = new Event("Testing for Java", "2024/03/28 1900", "2024/03/28 2100", "Mid");
        String expected = "[E][ ] Testing for Java Priority: Mid (from: Mar 28 2024 1900 to: Mar 28 2024 2100)";

        assertEquals(expected, testEvent.toString());
    }

    @Test
    public void toStringTest2() {
        Event testEvent = new Event("NUS FOOTBALL ICG", "2024/02/11 1200", "2024/02/11 2100", "High");
        String expected = "[E][ ] NUS FOOTBALL ICG Priority: High (from: Feb 11 2024 1200 to: Feb 11 2024 2100)";

        assertEquals(expected, testEvent.toString());
    }
}
