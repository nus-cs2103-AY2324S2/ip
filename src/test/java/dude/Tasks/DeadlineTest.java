package dude.Tasks;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class DeadlineTest {

    @Test
    public void testToString() {
        LocalDateTime dt = LocalDateTime.parse("2020-12-12T18:00");
        Deadline deadline = new Deadline("test", dt);
        assertEquals("[D][ ] test (by: Dec 12, 2020 @ 6:00PM)", deadline.toString());
    }

    @Test
    public void testEqualsCorretScenario() {
        LocalDateTime dt = LocalDateTime.parse("2020-12-12T18:00");
        Deadline deadline = new Deadline("test", dt);
        Deadline deadline2 = new Deadline("test", dt);
        assertEquals(deadline, deadline2);
    }

    @Test
    public void testEqualsWrongScenario() {
        LocalDateTime dt = LocalDateTime.parse("2020-12-12T18:00");
        Deadline deadline = new Deadline("test", dt);
        Deadline deadline2 = new Deadline("test2", dt);
        assertNotEquals(deadline, deadline2);
    }


}
