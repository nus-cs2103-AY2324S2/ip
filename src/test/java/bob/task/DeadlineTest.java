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
        assertFalse(new Deadline("",
                LocalDateTime.of(2024, Month.FEBRUARY, 12, 19, 37, 0))
                .isOccurringOn(LocalDate.of(2024, Month.FEBRUARY, 11)));
    }

    @Test
    public void isOccurringOn_sameDate_true() {
        assertTrue(new Deadline("",
                LocalDateTime.of(2024, Month.FEBRUARY, 12, 19, 37, 0))
                .isOccurringOn(LocalDate.of(2024, Month.FEBRUARY, 12)));
    }

    @Test
    public void isDueIn_moreThanDays_false() {
        assertFalse(new Deadline("", LocalDateTime.now().plusDays(9)).isDueIn(7));
    }

    @Test
    public void isDueIn_afterBy_false() {
        assertFalse(new Deadline("", LocalDateTime.now().minusDays(1)).isDueIn(0));
    }

    @Test
    public void isDueIn_negativeDays_false() {
        assertFalse(new Deadline("",
                LocalDateTime.of(2024, Month.FEBRUARY, 12, 19, 37, 0))
                .isDueIn(-1));
    }

    @Test
    public void isDueIn_todayNotDueYet_true() {
        assertTrue(new Deadline("", LocalDateTime.now().plusHours(1)).isDueIn(0));
    }

    @Test
    public void isDueIn_todayDue_false() {
        assertFalse(new Deadline("", LocalDateTime.now().minusHours(1)).isDueIn(0));
    }

    @Test
    public void toString_notDone_success() {
        assertEquals("[D][ ] a (by: Feb 12 2024 1937)", new Deadline("a",
                LocalDateTime.of(2024, Month.FEBRUARY, 12, 19, 37, 0))
                .toString());
    }

    @Test
    public void toString_done_success() {
        Deadline deadline = new Deadline("a",
                LocalDateTime.of(2024, Month.FEBRUARY, 12, 19, 37, 0));
        deadline.setDone(true);
        assertEquals("[D][X] a (by: Feb 12 2024 1937)", deadline.toString());
    }
}
