package jav.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void deadline_constructor_invalidParam() {
        // Params need to follow format
        try {
            assertEquals(0, new Deadline("", false));
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("Invalid param for deadline", e.getMessage());
        }
    }

    @Test
    public void deadline_updateMark_true() {
        Deadline d = new Deadline("do smt /by tmr", false);
        d.updateMark(true);
        assertEquals(d.isMarked(), true);
    }

    @Test
    public void deadline_updateMark_false() {
        Deadline d = new Deadline("do smt /by tmr", true);
        d.updateMark(false);
        assertEquals(d.isMarked(), false);
    }
}
