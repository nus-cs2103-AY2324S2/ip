package handler;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import anxi.command.AnxiException;
import anxi.handlers.Handler;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HandlerTest {
    private Handler handler = new Handler();

    @Test
    public void parseDateTime() throws AnxiException {
        assertEquals(LocalDateTime.parse("2024-02-12 1500", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")),
               handler.parseDateTime("2024-02-12 1500"));

        assertEquals(LocalDateTime.parse("2024-02-12 1500", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")),
                handler.parseDateTime("2024/02/12 15:00"));

        assertEquals(LocalDateTime.parse("2024-02-12 03:00 PM", DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a")),
                handler.parseDateTime("2024/02/12 03:00 PM"));
    }

    @Test
    public void parseTimeTest() throws AnxiException {
        assertEquals(LocalTime.parse("1500", DateTimeFormatter.ofPattern("HHmm")),
                handler.parseTime("1500"));

        assertEquals(LocalTime.parse("15:00", DateTimeFormatter.ofPattern("HH:mm")),
                handler.parseTime("15:00"));

        assertEquals(LocalTime.parse("03:00 PM", DateTimeFormatter.ofPattern("hh:mm a")),
                handler.parseTime("03:00 PM"));
    }

    @Test
    public void parseDateTest() throws AnxiException {
        assertEquals(LocalDate.parse("2025-02-12", DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                handler.parseDate("2025-02-12"));

        assertEquals(LocalDate.parse("2025/02/12", DateTimeFormatter.ofPattern("yyyy/MM/dd")),
                handler.parseDate("2025/02/12"));

        assertEquals(LocalDate.parse("02-12-2025", DateTimeFormatter.ofPattern("MM-dd-yyyy")),
                handler.parseDate("02-12-2025"));

        assertEquals(LocalDate.parse("02/12/2025", DateTimeFormatter.ofPattern("MM/dd/yyyy")),
                handler.parseDate("02/12/2025"));
    }

    @Test
    public void stringToIntTest() throws AnxiException {
        assertEquals(1, handler.stringToInt("1"));
    }
}
