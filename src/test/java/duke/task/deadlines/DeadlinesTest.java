package duke.task.deadlines;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DeadlinesTest {
    @Test
    public void testToString() {
        Deadlines deadlines = new Deadlines("Return book", LocalDate.parse("2019-12-12"));
        deadlines.markAsDone();
        assertEquals("  [D][X] Return book (by: Dec 12 2019)", deadlines.toString());
    }

    @Test
    public void testToFile() {
        Deadlines deadlines = new Deadlines("Return book", LocalDate.parse("2019-12-12"));
        deadlines.markAsDone();
        assertEquals("D|1|Return book|2019-12-12", deadlines.toFile());
    }
}
