package jade.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import jade.exception.JadeException;

public class RecurringTaskTest {
    @Test
    public void isSameDate() throws JadeException {
        assertTrue(new RecurringTask("group discussion",
                LocalDate.parse("2024-01-01"), LocalDate.parse("2024-01-31"),
                LocalTime.parse("09:00 am", DateTimeFormatter.ofPattern("hh:mm a")),
                LocalTime.parse("11:00 am", DateTimeFormatter.ofPattern("hh:mm a")),
                RecurringTask.TaskFreq.valueOf("Weekly"))
                .isSameDate(LocalDate.parse("2024-01-01")));
        assertTrue(new RecurringTask("group discussion",
                LocalDate.parse("2024-01-01"), LocalDate.parse("2024-01-31"),
                LocalTime.parse("09:00 am", DateTimeFormatter.ofPattern("hh:mm a")),
                LocalTime.parse("11:00 am", DateTimeFormatter.ofPattern("hh:mm a")),
                RecurringTask.TaskFreq.valueOf("Weekly"))
                .isSameDate(LocalDate.parse("2024-01-08")));
        assertFalse(new RecurringTask("group discussion",
                LocalDate.parse("2024-01-01"), LocalDate.parse("2024-01-31"),
                LocalTime.parse("09:00 am", DateTimeFormatter.ofPattern("hh:mm a")),
                LocalTime.parse("11:00 am", DateTimeFormatter.ofPattern("hh:mm a")),
                RecurringTask.TaskFreq.valueOf("Weekly"))
                .isSameDate(LocalDate.parse("2024-01-31")));
        assertFalse(new RecurringTask("group discussion",
                LocalDate.parse("2024-01-01"), LocalDate.parse("2024-01-31"),
                LocalTime.parse("09:00 am", DateTimeFormatter.ofPattern("hh:mm a")),
                LocalTime.parse("11:00 am", DateTimeFormatter.ofPattern("hh:mm a")),
                RecurringTask.TaskFreq.valueOf("Weekly"))
                .isSameDate(LocalDate.parse("2024-01-02")));
    }
    @Test
    public void dateFormatter() throws JadeException {
        assertEquals("Jan 1 2024", new RecurringTask("group discussion",
                LocalDate.parse("2024-01-01"), LocalDate.parse("2024-01-31"),
                LocalTime.parse("09:00 am", DateTimeFormatter.ofPattern("hh:mm a")),
                LocalTime.parse("11:00 am", DateTimeFormatter.ofPattern("hh:mm a")),
                RecurringTask.TaskFreq.valueOf("Weekly"))
                .dateFormatter(LocalDate.parse("2024-01-01")));
    }
    @Test
    public void timeFormatter() throws JadeException {
        assertEquals("01:00 am", new RecurringTask("group discussion",
                LocalDate.parse("2024-01-01"), LocalDate.parse("2024-01-31"),
                LocalTime.parse("09:00 am", DateTimeFormatter.ofPattern("hh:mm a")),
                LocalTime.parse("11:00 am", DateTimeFormatter.ofPattern("hh:mm a")),
                RecurringTask.TaskFreq.valueOf("Weekly"))
                .timeFormatter(LocalTime.parse("01:00 am", DateTimeFormatter.ofPattern("hh:mm a"))));
    }
    @Test
    public void testStringConversion() throws JadeException {
        assertEquals("[RT][ ] group discussion (from: Jan 1 2024 to: Jan 31 2024 | 09:00 am to 11:00 am Weekly)",
                new RecurringTask("group discussion",
                        LocalDate.parse("2024-01-01"), LocalDate.parse("2024-01-31"),
                        LocalTime.parse("09:00 am", DateTimeFormatter.ofPattern("hh:mm a")),
                        LocalTime.parse("11:00 am", DateTimeFormatter.ofPattern("hh:mm a")),
                        RecurringTask.TaskFreq.valueOf("Weekly")).toString());
        assertEquals("[RT][X] group discussion (from: Jan 1 2024 to: Jan 31 2024 | 09:00 am to 11:00 am Weekly)",
                new RecurringTask("group discussion",
                        LocalDate.parse("2024-01-01"), LocalDate.parse("2024-01-31"),
                        LocalTime.parse("09:00 am", DateTimeFormatter.ofPattern("hh:mm a")),
                        LocalTime.parse("11:00 am", DateTimeFormatter.ofPattern("hh:mm a")),
                        RecurringTask.TaskFreq.valueOf("Weekly"), true).toString());
    }
}
