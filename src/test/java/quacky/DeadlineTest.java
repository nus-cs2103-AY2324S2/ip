package quacky;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
public class DeadlineTest {
    @Test
    public void toFileString_success() {
        Deadline deadline = new Deadline("Finish Assignments", LocalDate.parse("2024-01-01"));
        String expectedOutput = "D | 1 | Finish Assignments | 2023-01-01";
        String actualOutput = deadline.toFileString();
        assertEquals(expectedOutput,actualOutput);
    }

    @Test
    public void toString_success() {
        Deadline deadline = new Deadline("Finish Assignments", LocalDate.parse("2024-01-01"));
        String expectedOutput = "[D][ ] Finish Assignments (by: Jan 1 2023)";
        String actualOutput = deadline.toString();
        assertEquals(expectedOutput,actualOutput);
    }

    @Test
    public void clashesWith_sameDate_returnsTrue() {
        Deadline deadline1 = new Deadline("Finish editing essay", LocalDate.parse("2024-01-01"));
        Deadline deadline2 = new Deadline("Finish writing script", LocalDate.parse("2024-01-01"));
        boolean expectedOutput = true;
        boolean actualOutput = deadline1.clashesWith(deadline2);
        assertEquals(expectedOutput,actualOutput);
    }
}
