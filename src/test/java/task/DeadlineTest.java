package task;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class DeadlineTest {
    @Test
    public void initialise_withoutBoolean() {
        Task task = new Deadline("test1", LocalDate.parse("2024-02-03"));

        assertFalse(task.getIsDone());
        assertEquals("test1", task.getDescription());
        assertEquals(" ", task.getStatusIcon());
    }

    @Test
    public void initialise_withBoolean() {
        Task task = new Deadline(true, "test2", LocalDate.parse("2024-02-03"));

        assertTrue(task.getIsDone());
        assertEquals("X", task.getStatusIcon());
    }

    @Test
    public void toStringTest() {
        Task task = new Deadline(true, "test3", LocalDate.parse("2024-02-03"));

        assertEquals("[D][X] test3 (by: 3 Feb 2024)", task.toString());
    }
}
