package guanguan;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void saveToTextTest() {
        Deadline deadline = new Deadline("Complete CS2103T", LocalDate.parse("2024-01-31"));
        deadline.markDone();

        assertEquals("D | 1 | Complete CS2103T | 2024-01-31", deadline.saveToText());
    }

    @Test
    public void toStringTest() {
        Deadline deadline = new Deadline("Complete CS2103T", LocalDate.parse("2024-01-31"));

        assertEquals("[D][ ] Complete CS2103T (by: Jan 31 2024)", deadline.toString());
    }
}
