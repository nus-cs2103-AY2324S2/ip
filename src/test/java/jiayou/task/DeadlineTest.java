package jiayou.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void testToStringForStore() {
        LocalDate by = LocalDate.parse("2020-01-01");
        Deadline deadline = new Deadline("Watch movies", by);
        String expected = "D | 0 | Watch movies | by2020-01-01";
        assertEquals(expected, deadline.toStringForStore());
    }

    @Test
    public void testToString() {
        LocalDate by = LocalDate.parse("2020-01-01");
        Deadline deadline = new Deadline("Watch movies", by);
        String expected = "[D][ ] Watch movies (by: Jan 1 2020)";
        assertEquals(expected, deadline.toString());
    }

    @Test
    public void testToDoDescription() {
        LocalDate by = LocalDate.parse("2020-01-01");
        String description = "Watch movies";
        Deadline deadline = new Deadline("Watch movies", by);
        assertEquals(description, deadline.getDescription());
    }

    @Test
    public void testEquals() {
        LocalDate by = LocalDate.parse("2020-01-01");
        LocalDate anotherBy = LocalDate.parse("2020-01-01");

        Deadline deadline = new Deadline("Watch movies", by);
        Deadline deadlineSame = new Deadline("Watch movies", by);
        Deadline deadlineDiffDesc = new Deadline("Watch", by);
        Deadline deadlineDiffBy = new Deadline("Watch", anotherBy);

        assertEquals(true, deadline.equals(deadlineSame));
        assertEquals(false, deadline.equals(deadlineDiffBy));
        assertEquals(false, deadline.equals(deadlineDiffDesc));
    }
}