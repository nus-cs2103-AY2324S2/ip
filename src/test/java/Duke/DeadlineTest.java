package Duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


public class DeadlineTest {

    @Test
    public void testInRange() {
        Deadline deadline = new Deadline("Test", LocalDate.of(2022, 1, 1));
        //assert true if deadline on the first day of date range
        assertTrue(deadline.inRange(LocalDate.of(2022, 1, 1),
                LocalDate.of(2023, 1, 1)));
        //assert true if deadline on the last day of date range
        assertTrue(deadline.inRange(LocalDate.of(2021, 1, 1),
                LocalDate.of(2022, 1, 1)));
        //assert false if deadline out of date range
        assertFalse(deadline.inRange(LocalDate.of(2022, 2, 1),
                LocalDate.of(2023, 1, 1)));
    }
}
