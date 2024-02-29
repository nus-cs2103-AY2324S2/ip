package bob.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.jupiter.api.Test;

public class PeriodTest {
    @Test
    public void getStorageFormat() {
        assertEquals("P | false | a | 2024-02-11T19:37:00 | 2024-02-12T19:37:00", new Period("a",
                LocalDateTime.of(2024, Month.FEBRUARY, 11, 19, 37, 0),
                LocalDateTime.of(2024, Month.FEBRUARY, 12, 19, 37, 0))
                .getStorageFormat());

        Period period = new Period("a",
                LocalDateTime.of(2024, Month.FEBRUARY, 11, 19, 37, 0),
                LocalDateTime.of(2024, Month.FEBRUARY, 12, 19, 37, 0));
        period.setDone(true);
        assertEquals("P | true | a | 2024-02-11T19:37:00 | 2024-02-12T19:37:00", period.getStorageFormat());
    }

    @Test
    public void isDueIn() {
        assertFalse(new Period("",
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(9)).isDueIn(7));
        assertFalse(new Period("",
                LocalDateTime.now().minusDays(2),
                LocalDateTime.now().minusDays(1)).isDueIn(0));
        assertFalse(new Period("",
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(1)).isDueIn(-1));
        assertTrue(new Period("",
                LocalDateTime.now().minusHours(1),
                LocalDateTime.now().plusHours(1)).isDueIn(0));
        assertFalse(new Period("",
                LocalDateTime.now().minusHours(2),
                LocalDateTime.now().minusHours(1)).isDueIn(0));
        assertFalse(new Period("",
                LocalDateTime.now().plusHours(1),
                LocalDateTime.now().plusHours(2)).isDueIn(0));
    }

    @Test
    public void toString_notDone() {
        assertEquals("[P][ ] a (from: Feb 11 2024 1937 to: Feb 12 2024 1937)", new Period("a",
                LocalDateTime.of(2024, Month.FEBRUARY, 11, 19, 37, 0),
                LocalDateTime.of(2024, Month.FEBRUARY, 12, 19, 37, 0))
                .toString());
    }

    @Test
    public void toString_done() {
        Period period = new Period("a",
                LocalDateTime.of(2024, Month.FEBRUARY, 11, 19, 37, 0),
                LocalDateTime.of(2024, Month.FEBRUARY, 12, 19, 37, 0));
        period.setDone(true);
        assertEquals("[P][X] a (from: Feb 11 2024 1937 to: Feb 12 2024 1937)", period.toString());
    }
}
