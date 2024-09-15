package duke.task.deadlines;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class DeadlinesTest {
    @Test
    public void testToString() {
        LocalDateTime by = LocalDateTime.parse("2012-12-12 12:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        Deadlines deadlines = new Deadlines("Return book", by);
        deadlines.markAsDone();
        assertEquals("  [D][X] Return book (by: Dec 12 2012 12:00)", deadlines.toString());
    }

    @Test
    public void testToFile() {
        LocalDateTime by = LocalDateTime.parse("2012-12-12 12:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        Deadlines deadlines = new Deadlines("Return book", by);
        deadlines.markAsDone();
        assertEquals("D|1|Return book|2012-12-12 12:00", deadlines.toFile());
    }
}
