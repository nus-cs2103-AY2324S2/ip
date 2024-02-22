package jade.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jade.exception.JadeException;

/**
 * The <code>RecurringTaskTest</code> class contains unit tests for the <code>RecurringTask</code> class,
 * which represents a recurring task within the task management system.
 * These tests verify various functionalities of the <code>RecurringTask</code> class,
 * including determining if the task occurs on a specific date,
 * formatting the task's date and time, and converting the task to a string representation.
 * The above comment is generated using ChatGPT 3.5 using the prompt:
 * "generate a block comment for the RecurringTaskTest class:{code}".
 * Modified by author for higher quality.
 */
public class RecurringTaskTest {
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private RecurringTask recurringTask1;
    private RecurringTask recurringTask2;
    private RecurringTask.TaskFreq taskFreq;
    @BeforeEach
    public void setUp() throws JadeException {
        startDate = LocalDate.parse("2024-01-01");
        endDate = LocalDate.parse("2024-01-31");
        startTime = LocalTime.parse("09:00 am", DateTimeFormatter.ofPattern("hh:mm a", Locale.UK));
        endTime = LocalTime.parse("11:00 am", DateTimeFormatter.ofPattern("hh:mm a", Locale.UK));
        taskFreq = RecurringTask.TaskFreq.valueOf("Weekly");
        recurringTask1 = new RecurringTask("group discussion", startDate, endDate, startTime, endTime, taskFreq);
        recurringTask2 = new RecurringTask("group discussion", startDate, endDate, startTime, endTime, taskFreq, true);
    }
    @Test
    public void isSameDate() throws JadeException {
        assertTrue(recurringTask1.isSameDate(LocalDate.parse("2024-01-01")));
        assertTrue(recurringTask1.isSameDate(LocalDate.parse("2024-01-08")));
        assertFalse(recurringTask1.isSameDate(LocalDate.parse("2024-01-31")));
        assertFalse(recurringTask1.isSameDate(LocalDate.parse("2024-01-02")));
    }
    @Test
    public void dateFormatter() throws JadeException {
        assertEquals("Jan 1 2024", recurringTask1.dateFormatter(startDate));
        assertEquals("Jan 31 2024", recurringTask1.dateFormatter(endDate));
    }
    @Test
    public void timeFormatter() throws JadeException {
        assertEquals("09:00 am", recurringTask1.timeFormatter(startTime));
        assertEquals("11:00 am", recurringTask1.timeFormatter(endTime));
    }
    @Test
    public void testStringConversion() throws JadeException {
        assertEquals("[RT][ ] group discussion (from: Jan 1 2024 to: Jan 31 2024 | 09:00 am to 11:00 am Weekly)",
                recurringTask1.toString());
        assertEquals("[RT][X] group discussion (from: Jan 1 2024 to: Jan 31 2024 | 09:00 am to 11:00 am Weekly)",
                recurringTask2.toString());
    }
}
