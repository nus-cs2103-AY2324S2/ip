package duke;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeadlineTest {

    @Test
    public void validDateTimeInput() {
        Deadline deadline = new Deadline("Finish project", "15/02/2024 2359");
        assertEquals(LocalDateTime.of(2024, 2, 15, 23, 59), deadline.by);
        assertNull(deadline.dayOfWeek);

    }
    @Test
    public void validDayOfWeekInput() {
        Deadline deadline = new Deadline("Submit report", "Monday");
        assertNull(deadline.by);
        assertEquals(DayOfWeek.MONDAY, deadline.dayOfWeek);
    }

    @Test
    public void invalidInput() {
        Deadline deadline = new Deadline("Monday", "hello");
    }

    @Test
    public void toString_withDateTime() {
        Deadline deadline = new Deadline("Finish assignment", "24/01/2025 1400");
        String expected = "[D][ ] Finish assignment (by: 24 Jan 2025 2:00 PM)";
        assertEquals(expected, deadline.toString());
    }

    @Test
    public void toString_withDayOfWeek() {
        Deadline deadline = new Deadline("Meeting", "Wednesday");
        String expected = "[D][ ] Meeting (by: WEDNESDAY)";
        assertEquals(expected, deadline.toString());

    }

}
