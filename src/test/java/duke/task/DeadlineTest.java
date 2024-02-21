package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;


public class DeadlineTest {
    @Test
    public void getDeadlineForDisplay_validFormat_returnsFormattedDateTime() {
        String content = "Project";
        String deadline = "2022-12-31 12:31";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime parsedDateTime = LocalDateTime.parse(deadline, formatter);
        Deadline newTask = new Deadline(content, parsedDateTime);
        assertEquals("DECEMBER 31 2022 12:31PM", newTask.getDeadlineForDisplay());
    }
}
