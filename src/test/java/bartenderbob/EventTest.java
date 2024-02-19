package bartenderbob;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EventTest {
    @Test
    public void event_invalidDateFormat_exceptionThrown() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Event("Testing!", "Monday", "Friday");
        });
    }
    @Test
    public void event_invalidFromMonth_exceptionThrown() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Event("Testing!", "2019-13-10", "2019-10-10");
        });
    }
    @Test
    public void event_invalidByMonth_exceptionThrown() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Event("Testing!", "2019-12-10", "2019-13-10");
        });
    }
    @Test
    public void event_fromDayExceedBounds_exceptionThrown() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Event("Testing!", "2019-10-40", "2019-11-10");
        });
    }
    @Test
    public void event_byDayExceedBounds_exceptionThrown() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Event("Testing!", "2019-10-10", "2019-11-50");
        });
    }
    @Test
    public void event_fromDayEqualsZero_exceptionThrown() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Event("Testing!", "2019-10-00", "2019-11-10");
        });
    }
    @Test
    public void event_byDayEqualsZero_exceptionThrown() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Event("Testing!", "2019-10-10", "2019-11-00");
        });
    }
    @Test
    public void event_byIsBeforeFrom_exceptionThrown() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Event("Testing!", "2019-10-10", "2019-10-09");
        });
    }
    @Test
    public void hasNoClash_eventsDoNotOverlap_trueReturned() {
        Event firstEvent = new Event("test 1", "2024-02-19", "2024-02-20");
        Event secondEvent = new Event("test 2", "2024-02-21", "2024-02-22");
        boolean result = firstEvent.hasNoClash(secondEvent);
        assertTrue(result, "Events should not overlap");
    }
    @Test
    public void hasNoClash_eventsOverlap_falseReturned() {
        Event firstEvent = new Event("test 1", "2024-02-19", "2024-02-21");
        Event secondEvent = new Event("test 2", "2024-02-20", "2024-02-22");
        boolean result = firstEvent.hasNoClash(secondEvent);
        assertFalse(result, "Events should not overlap");
    }
    @Test
    public void event_validUserInputs_classCreated() {
        Event test = new Event("Testing!", "2019-10-10", "2019-10-12");
        assertNotNull(test, "Event instance should not be null");
        assertEquals(test.show(), "[E][ ] Testing! (from: Oct 10 2019 to: Oct 12 2019)");
    }
}
