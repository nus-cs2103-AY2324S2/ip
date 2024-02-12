package bob.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeadlineTest {
    @Test
    public void toStorageFormat_notDone_success() {
        assertEquals("D | false | a | 2024-02-12T19:37:00", new Deadline("a",
                LocalDateTime.of(2024, Month.FEBRUARY, 12, 19, 37, 0))
                .toStorageFormat());
    }

    @Test
    public void toStorageFormat_done_success() {
        Deadline deadline = new Deadline("a",
                LocalDateTime.of(2024, Month.FEBRUARY, 12, 19, 37, 0));
        deadline.setDone(true);
        assertEquals("D | true | a | 2024-02-12T19:37:00", deadline.toStorageFormat());
    }

    @Test
    public void isOccurringOn_differentDate_false() {
        Deadline deadline = new Deadline("",
                LocalDateTime.of(2024, Month.FEBRUARY, 12, 19, 37, 0));
        assertFalse(deadline.isOccurringOn(LocalDate.of(2024, Month.FEBRUARY, 11)));
    }

    @Test
    public void isOccurringOn_sameDate_true() {
        Deadline deadline = new Deadline("",
                LocalDateTime.of(2024, Month.FEBRUARY, 12, 19, 37, 0));
        assertTrue(deadline.isOccurringOn(LocalDate.of(2024, Month.FEBRUARY, 12)));
    }
}
