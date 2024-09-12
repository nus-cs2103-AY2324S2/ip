package task;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class EventTest {
    @Test
    public void initialise_withoutBoolean() {
        Task task = new Event("test1", LocalDate.parse("2024-02-03"), LocalDate.parse("2024-04-05"));

        assertFalse(task.getIsDone());
        assertEquals("test1", task.getDescription());
        assertEquals(" ", task.getStatusIcon());
    }

    @Test
    public void initialise_withBoolean() {
        Task task = new Event(true, "test2", LocalDate.parse("2024-02-03"),
                LocalDate.parse("2024-04-05"));

        assertTrue(task.getIsDone());
        assertEquals("X", task.getStatusIcon());
    }

    @Test
    public void toStringTest() {
        Task task = new Event(true, "test3", LocalDate.parse("2024-02-03"),
                LocalDate.parse("2023-04-05"));

        assertEquals("[E][X] test3 (from: 3 Feb 2024, to: 5 Apr 2023)", task.toString());
    }
}
