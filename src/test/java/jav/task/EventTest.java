package jav.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void event_constructor_invalidParam() {
        // Params need to follow format
        try {
            assertEquals(0, new Event("", false));
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("Invalid param for event", e.getMessage());
        }

        // date of /from parameter cannot be after /to parameter
        try {
            assertEquals(0, new Event("do smt /to tmr /from today", false));
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("Invalid param for event", e.getMessage());
        }
    }

    @Test
    public void event_updateMark_true() {
        Event e = new Event("do smt /from today /to tmr", false);
        e.updateMark(true);
        assertEquals(e.isMarked(), true);
    }

    @Test
    public void event_updateMark_false() {
        Event e = new Event("do smt /from today /to tmr", true);
        e.updateMark(false);
        assertEquals(e.isMarked(), false);
    }
}
