package kai;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;



public class DeadlineTest {

    @Test
    public void validDateTimeInput() {
        Deadline deadline = new Deadline("Finish project", "15/02/2024 2359");
        assert(LocalDateTime.of(2024, 2, 15, 23, 59).equals(deadline.by));
        assert(deadline.dayOfWeek) == null;

    }
    @Test
    public void validDayOfWeekInput() {
        Deadline deadline = new Deadline("Submit report", "Monday");
        assert(deadline.by) == null;
        assert(DayOfWeek.MONDAY.equals(deadline.dayOfWeek));
    }

    @Test
    public void invalidInput() {
        Deadline deadline = new Deadline("Monday", "hello");
    }

    @Test
    public void toString_withDateTime() {
        Deadline deadline = new Deadline("Finish assignment", "24/01/2025 1400");
        String expected = "[D][ ] Finish assignment (by: 24 Jan 2025 2:00 PM)";
        assert(expected.equals(deadline.toString()));
    }

    @Test
    public void toString_withDayOfWeek() {
        Deadline deadline = new Deadline("Meeting", "Wednesday");
        String expected = "[D][ ] Meeting (by: WEDNESDAY)";
        assert(expected.equals(deadline.toString()));

    }

}
