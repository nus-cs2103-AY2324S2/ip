package martin;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DeadlineTest {

    @Test
    public void testConstructor() {
        LocalDate dueDate = LocalDate.of(2023, 4, 15);
        Deadline deadline = new Deadline("Submit project report", dueDate);
        assertEquals("Submit project report", deadline.getDescription());
    }

    @Test
    public void testToString() {
        LocalDate dueDate = LocalDate.of(2023, 5, 20);
        Deadline deadline = new Deadline("Pay bills", dueDate);
        String formattedDate = dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String expected = "[D][ ] Pay bills (by: " + formattedDate + ")";
        assertEquals(expected, deadline.toString());
    }

    @Test
    public void testToFileString() {
        LocalDate dueDate = LocalDate.of(2023, 6, 1);
        Deadline deadline = new Deadline("Finish coding assignment", dueDate);
        String expected = "D | 0 | Finish coding assignment | " + dueDate;
        assertEquals(expected, deadline.toFileString());
    }
}
