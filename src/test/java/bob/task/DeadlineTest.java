package bob.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void getStorageFormat() {
        assertEquals("D | false | a | 2024-02-12T19:37:00", new Deadline("a",
                LocalDateTime.of(2024, Month.FEBRUARY, 12, 19, 37, 0))
                .getStorageFormat());

        Deadline deadline = new Deadline("a",
                LocalDateTime.of(2024, Month.FEBRUARY, 12, 19, 37, 0));
        deadline.setDone(true);
        assertEquals("D | true | a | 2024-02-12T19:37:00", deadline.getStorageFormat());
    }

    @Test
    public void isOccurringOn() {
        assertFalse(new Deadline("",
                LocalDateTime.of(2024, Month.FEBRUARY, 12, 19, 37, 0))
                .isOccurringOn(LocalDate.of(2024, Month.FEBRUARY, 11)));

        assertTrue(new Deadline("",
                LocalDateTime.of(2024, Month.FEBRUARY, 12, 19, 37, 0))
                .isOccurringOn(LocalDate.of(2024, Month.FEBRUARY, 12)));
    }

    @Test
    public void isDueIn() {
        assertFalse(new Deadline("", LocalDateTime.now().plusDays(9)).isDueIn(7));
        assertFalse(new Deadline("", LocalDateTime.now().minusDays(1)).isDueIn(0));
        assertFalse(new Deadline("",
                LocalDateTime.of(2024, Month.FEBRUARY, 12, 19, 37, 0))
                .isDueIn(-1));
        assertTrue(new Deadline("", LocalDateTime.now().plusHours(1)).isDueIn(0));
        assertFalse(new Deadline("", LocalDateTime.now().minusHours(1)).isDueIn(0));
    }

    @Test
    public void toString_notDone() {
        assertEquals("[D][ ] a (by: Feb 12 2024 1937)", new Deadline("a",
                LocalDateTime.of(2024, Month.FEBRUARY, 12, 19, 37, 0))
                .toString());
    }

    @Test
    public void toString_done() {
        Deadline deadline = new Deadline("a",
                LocalDateTime.of(2024, Month.FEBRUARY, 12, 19, 37, 0));
        deadline.setDone(true);
        assertEquals("[D][X] a (by: Feb 12 2024 1937)", deadline.toString());
    }
}
