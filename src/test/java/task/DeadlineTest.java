package task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.Test;


public class DeadlineTest {
    @Test
    public void toString_correctFormat_success() {
        // calling toString results in the correct format
        assertEquals("[D][ ] feed dog (by: Feb 06 2024)",
                     new Deadline("feed dog", LocalDate.of(2024, Month.FEBRUARY, 6)).toString());
    }

}
