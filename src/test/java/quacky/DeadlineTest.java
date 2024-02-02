package quacky;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTest {
    @Test
    public void toFileString_success() {
        Deadline deadline = new Deadline("Finish Assignments", LocalDate.parse("2023-01-01"));
        assertEquals(deadline.toFileString(), "D | 1 | Finish Assignments | 2023-01-01");
    }

    @Test
    public void toString_success() {
        Deadline deadline = new Deadline("Finish Assignments", LocalDate.parse("2023-01-01"));
        assertEquals(deadline.toString(), "[D][ ] Finish Assignments (by: Jan 1 2023)");
    }
}
