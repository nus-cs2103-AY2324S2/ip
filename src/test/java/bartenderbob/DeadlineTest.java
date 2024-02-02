package bartenderbob;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeadlineTest {
    @Test
    public void Deadline_invalidString_exceptionThrown() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Deadline("Testing!", "Tuesday");
        });
    }
    @Test
    public void Deadline_invalidMonth_exceptionThrown() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Deadline("Testing!", "2019-13-10");
        });
    }
    @Test
    public void Deadline_dayExceedBounds_exceptionThrown() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Deadline("Testing!", "2019-13-40");
        });
    }
    @Test
    public void Deadline_dayEqualZero_exceptionThrown() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Deadline("Testing!", "2019-13-0");
        });
    }
    @Test
    public void Deadline_validString_classCreated() {
        Deadline test = new Deadline("Testing!", "2019-10-10");
        assertNotNull(test, "Deadline instance should not be null");
        assertEquals(test.show(), "[D][ ] Testing! (by: Oct 10 2019)");
    }
}

