package tasks;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testToStringConversion() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime by = LocalDateTime.parse("2024-02-19 1800", formatter);
        assertEquals("[D][ ] eat (by: Feb 19 2024 | 1800)", new Deadline("eat", false, by).toString());
    }
    @Test
    public void testToFileStringConversion() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime by = LocalDateTime.parse("2024-02-19 1800", formatter);
        assertEquals("D| 0|eat|2024-02-19 18:00", new Deadline("eat", false, by).toFileString());
    }
}
