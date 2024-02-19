package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import fredricksen.tasks.Deadline;

public class DeadlineTaskTest {

    /**
     * Checks if toString method for Deadline class returns string with correct format if successful.
     */
    @Test
    public void deadlineToString_correctFormat_success() {
        String dateTime = "2/12/2019 1800";
        Deadline newTask = new Deadline("deadline homework /by " + dateTime, "D", false);
        assertEquals("[D][] homework (by: Dec 2 2019, 06:00 PM)", newTask.toString());
    }

    @Test
    public void deadlineToString_incorrectTimeFormatHandled_failure() {
        String dateTime = "2/12/2019 100";
        Deadline newTask = new Deadline("deadline homework /by " + dateTime, "D", false);
        assertEquals("Invalid Date", newTask.toString());
    }

    @Test
    public void deadlineToString_incorrectDateFormatHandled_failure() {
        String dateTime = "2/123/2019 1300";
        Deadline newTask = new Deadline("deadline homework /by " + dateTime, "D", false);
        assertEquals("Invalid Date", newTask.toString());
    }
}
