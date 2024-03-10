package micromanager.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import micromanager.exceptions.DukeException;

public class DeadlineTest {

    @Test
    public void testUnmarkedFileStringConversion() throws DukeException {
        Deadline testDeadline = new Deadline("task", LocalDate.parse("2025-01-02"));
        assertEquals("[D][ ] task (by: Jan 2 2025)", testDeadline.toString());
    }

    @Test
    public void testMarkedFileStringConversion() {
        Deadline testDeadline = new Deadline("task", LocalDate.parse("2025-01-02"));
        testDeadline.markDone();
        assertEquals("[D][X] task (by: Jan 2 2025)", testDeadline.toString());
    }
}
