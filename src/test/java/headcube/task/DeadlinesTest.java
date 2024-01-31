package headcube.task;
import headcube.Deadlines;
import headcube.Task;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlinesTest {
    @Test
    public void testToString() {
        LocalDate date = LocalDate.of(2024, 01, 01);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String formattedDate = date.format(formatter);
        assertEquals("[D][ ] hi (by: Jan 01 2024)",
                new Deadlines("hi", formattedDate).toString());
    }

    @Test
    public void testToFileFormat() {
        LocalDate date = LocalDate.of(2024, 01, 01);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String formattedDate = date.format(formatter);
        assertEquals("D | 0 | hi | (by: Jan 01 2024)",
                new Deadlines("hi", formattedDate).toFileFormat());
    }

}
