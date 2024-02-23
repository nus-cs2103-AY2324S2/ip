package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import anxi.tasks.Deadline;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void createDeadlineTest() {
        assertEquals("[D][ ] return book (by: Jan 25 2024 05:00 PM)",
                new Deadline("return book", LocalDateTime.parse("25-01-2024 17:00",
                                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))).toString());
    }

    @Test
    public void createDeadlineWithBoolTest() {
        assertEquals("[D][X] return book (by: Jan 25 2024 05:00 PM)",
                new Deadline("return book", true, "25-01-2024 17:00").toString());

        assertEquals("[D][ ] return book (by: Jan 25 2024 05:00 PM)",
                new Deadline("return book", false, "25/01/2024 1700").toString());

        assertEquals("[D][ ] return book (by: Jan 25 2024 05:00 PM)",
                new Deadline("return book", false, "25/01/2024 05:00 PM").toString());
    }

    @Test
    public void testSaveFileSyntax() {
        assertEquals("D | 0 | return book | 2024-01-25 17:00",
                new Deadline("return book", LocalDateTime.parse("2024/01/25 1700",
                        DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"))).saveFileString());
    }
}
