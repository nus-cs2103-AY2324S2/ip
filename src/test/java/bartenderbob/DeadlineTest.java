package bartenderbob;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void deadline_invalidDateFormat_exceptionThrown() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Deadline("Testing!", "Tuesday");
        });
    }
    @Test
    public void deadline_invalidMonth_exceptionThrown() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Deadline("Testing!", "2019-13-10");
        });
    }
    @Test
    public void deadline_dayExceedBounds_exceptionThrown() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Deadline("Testing!", "2019-10-40");
        });
    }
    @Test
    public void deadline_dayEqualZero_exceptionThrown() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Deadline("Testing!", "2019-13-00");
        });
    }
    @Test
    public void deadline_validDateFormat_classCreated() {
        Deadline test = new Deadline("Testing!", "2019-10-10");
        assertNotNull(test, "Deadline instance should not be null");
        assertEquals(test.show(), "[D][ ] Testing! (by: Oct 10 2019)");
    }
}

