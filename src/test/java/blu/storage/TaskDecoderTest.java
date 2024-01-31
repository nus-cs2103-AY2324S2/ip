package blu.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import blu.exception.BluException;
import blu.task.Deadline;
import blu.task.Event;
import blu.task.Task;
import blu.task.ToDo;

public class TaskDecoderTest {
    private static final DateTimeFormatter INPUT_DATETIMEFORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    @Test
    public void fromCsv_invalidFormat_exceptionThrown() {
        String csv = "invalid,format";
        assertThrows(BluException.class, () -> TaskDecoder.fromCsv(csv));
    }

    @Test
    public void fromCsv_invalidDateTimeFormat_exceptionThrown() {
        String csv = "D,F,Submit report,8pm 02-12-24";
        assertThrows(BluException.class, () -> TaskDecoder.fromCsv(csv));
    }

    @Test
    public void fromCsv_validToDo_success() throws BluException {
        String csv = "T,F,Read book";
        Task task = TaskDecoder.fromCsv(csv);
        assertEquals(task, new ToDo("Read book"));
    }

    @Test
    public void fromCsv_validMarkedTask_success() throws BluException {
        String csv = "T,T,Read book";
        Task task = TaskDecoder.fromCsv(csv);
        String expectedString = "[T][X] Read book";
        assertEquals(expectedString, task.toString());
    }

    @Test
    public void fromCsv_validDeadline_success() throws BluException {
        String csv = "D,F,Submit report,2024-08-30T23:59";
        LocalDateTime expectedBy = LocalDateTime.parse("30-08-2024 23:59", INPUT_DATETIMEFORMAT);
        Task task = TaskDecoder.fromCsv(csv);
        assertEquals(task, new Deadline("Submit report", expectedBy));
    }

    @Test
    public void fromCsv_validEvent_success() throws BluException {
        String csv = "E,F,Attend Event,2024-08-30T12:00,2024-08-30T23:59";
        LocalDateTime expectedFrom = LocalDateTime.parse("30-08-2024 12:00", INPUT_DATETIMEFORMAT);
        LocalDateTime expectedTo = LocalDateTime.parse("30-08-2024 23:59", INPUT_DATETIMEFORMAT);
        Task task = TaskDecoder.fromCsv(csv);
        assertEquals(task, new Event("Attend Event", expectedFrom, expectedTo));
    }
}
