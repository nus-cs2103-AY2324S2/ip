package jade.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.junit.jupiter.api.Test;
public class DeadlineTest {
    @Test
    public void isSameDate() {
        assertTrue(new Deadline("read a book",
                LocalDateTime.parse("2024-01-01 01:00 pm",
                        DateTimeFormatter.ofPattern("uuuu-MM-dd hh:mm a", Locale.UK)))
                .isSameDate(LocalDate.parse("2024-01-01")));
        assertFalse(new Deadline("read a book",
                LocalDateTime.parse("2024-01-01 01:00 pm",
                        DateTimeFormatter.ofPattern("uuuu-MM-dd hh:mm a", Locale.UK)))
                .isSameDate(LocalDate.parse("2024-02-01")));
    }

    @Test
    public void dateTimeFormatter() {
        assertEquals("Jan 1 2024 01:00 pm", new Deadline("read a book",
                LocalDateTime.parse("2024-01-01 01:00 pm",
                        DateTimeFormatter.ofPattern("uuuu-MM-dd hh:mm a", Locale.UK))).dateTimeFormatter());
    }

    @Test
    public void testStringConversion() {
        assertEquals("[D][ ] read a book (by: Nov 1 2024 11:00 pm)", new Deadline("read a book",
                LocalDateTime.parse("2024-11-01 11:00 pm",
                        DateTimeFormatter.ofPattern("uuuu-MM-dd hh:mm a", Locale.UK))).toString());
        assertEquals("[D][X] read two books (by: Jan 1 2024 01:00 pm)", new Deadline("read two books",
                LocalDateTime.parse("2024-01-01 01:00 pm",
                        DateTimeFormatter.ofPattern("uuuu-MM-dd hh:mm a", Locale.UK)), true).toString());
    }
}
