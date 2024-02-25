package tasks;

import org.junit.jupiter.api.Test;
import task.Deadline;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void newDeadlineTask() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        LocalDateTime by = LocalDateTime.parse("2/12/2023 1800", formatter);
        Deadline deadline = new Deadline("read book", false, by );
        assertEquals("[D] [ ] read book (by:Dec 02 2023 18:00)", deadline.toString());
    }

    @Test
    public void markEvent() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        LocalDateTime by = LocalDateTime.parse("2/12/2023 1800", formatter);
        Deadline deadline = new Deadline("read book", false, by );
        deadline.markAsDone();
        assertEquals("[D] [X] read book (by:Dec 02 2023 18:00)", deadline.toString());
    }
}
