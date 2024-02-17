package ben.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DeadlineTest {

    @Test
    public void testSaveTask() {
        LocalDate deadlineDate = LocalDate.parse("2024-02-10");
        Deadline deadline = new Deadline(false, "Submit report", deadlineDate);
        assertEquals("D | 0 | Submit report | 2024-02-10", deadline.saveTask());
    }

    @Test
    public void testToString() {
        LocalDate deadlineDate = LocalDate.parse("2024-02-15");
        Deadline deadline = new Deadline(false, "Project deadline", deadlineDate);
        assertEquals("[D][ ] Project deadline (by: Feb 15 2024)", deadline.toString());
    }

    @Test
    public void testSaveTaskCompletedDeadline() {
        LocalDate deadlineDate = LocalDate.parse("2024-03-01");
        Deadline deadline = new Deadline(true, "Complete assignment", deadlineDate);
        assertEquals("D | 1 | Complete assignment | 2024-03-01", deadline.saveTask());
    }

    @Test
    public void testToStringCompletedDeadline() {
        LocalDate deadlineDate = LocalDate.parse("2024-03-05");
        Deadline deadline = new Deadline(true, "Final submission", deadlineDate);
        assertEquals("[D][X] Final submission (by: Mar 5 2024)", deadline.toString());
    }

    @Test
    public void testInvalidDate() {
        assertThrows(DateTimeParseException.class, () -> {
            new Deadline(false, "Invalid date", LocalDate.parse("InvalidDate"));
        });
    }
}

