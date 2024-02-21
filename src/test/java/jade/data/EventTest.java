package jade.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.junit.jupiter.api.Test;

import jade.exception.JadeException;

public class EventTest {
    @Test
    public void isSameDate() throws JadeException {
        assertTrue(new Event("team meeting",
                LocalDateTime.parse("2024-01-01 09:00 am",
                        DateTimeFormatter.ofPattern("uuuu-MM-dd hh:mm a", Locale.UK)),
                LocalDateTime.parse("2024-01-02 11:00 am",
                        DateTimeFormatter.ofPattern("uuuu-MM-dd hh:mm a", Locale.UK)))
                .isSameDate(LocalDate.parse("2024-01-01")));
        assertTrue(new Event("team meeting",
                LocalDateTime.parse("2024-01-01 09:00 am",
                        DateTimeFormatter.ofPattern("uuuu-MM-dd hh:mm a", Locale.UK)),
                LocalDateTime.parse("2024-01-02 11:00 am",
                        DateTimeFormatter.ofPattern("uuuu-MM-dd hh:mm a", Locale.UK)))
                .isSameDate(LocalDate.parse("2024-01-02")));
        assertFalse(new Event("team meeting",
                LocalDateTime.parse("2024-01-01 09:00 am",
                        DateTimeFormatter.ofPattern("uuuu-MM-dd hh:mm a", Locale.UK)),
                LocalDateTime.parse("2024-01-02 11:00 am",
                        DateTimeFormatter.ofPattern("uuuu-MM-dd hh:mm a", Locale.UK)))
                .isSameDate(LocalDate.parse("2024-01-03")));
    }
    @Test
    public void dateTimeFormatter() throws JadeException {
        assertEquals("Jan 1 2024 09:00 am", new Event("team meeting",
                LocalDateTime.parse("2024-01-01 09:00 am",
                        DateTimeFormatter.ofPattern("uuuu-MM-dd hh:mm a", Locale.UK)),
                LocalDateTime.parse("2024-01-01 11:00 am",
                        DateTimeFormatter.ofPattern("uuuu-MM-dd hh:mm a", Locale.UK)))
                .dateTimeFormatter(LocalDateTime.parse("2024-01-01 09:00 am",
                DateTimeFormatter.ofPattern("uuuu-MM-dd hh:mm a", Locale.UK))));
    }
    @Test
    public void testStringConversion() throws JadeException {
        assertEquals("[E][ ] team meeting (from: Jan 1 2024 09:00 am to: Jan 1 2024 11:00 am)",
                new Event("team meeting",
                LocalDateTime.parse("2024-01-01 09:00 am",
                        DateTimeFormatter.ofPattern("uuuu-MM-dd hh:mm a", Locale.UK)),
                LocalDateTime.parse("2024-01-01 11:00 am",
                DateTimeFormatter.ofPattern("uuuu-MM-dd hh:mm a", Locale.UK))).toString());
        assertEquals("[E][X] team meeting (from: Jan 1 2024 09:00 am to: Jan 1 2024 11:00 am)",
                new Event("team meeting",
                LocalDateTime.parse("2024-01-01 09:00 am",
                        DateTimeFormatter.ofPattern("uuuu-MM-dd hh:mm a", Locale.UK)),
                LocalDateTime.parse("2024-01-01 11:00 am",
                        DateTimeFormatter.ofPattern("uuuu-MM-dd hh:mm a", Locale.UK)), true).toString());
    }
}
