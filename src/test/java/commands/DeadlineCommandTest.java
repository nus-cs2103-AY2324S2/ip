package commands;

import org.junit.jupiter.api.Test;
import tasks.Deadline;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineCommandTest {
    @Test
    public void deadlineTask_InTaskList() {
        Deadline deadline = new Deadline("Eat",
                LocalDateTime.of(2023, 5, 11, 14, 0));
        assertEquals("[D][ ] Eat (by: May 11 2023 | 02:00 PM)", deadline.toString());
    }

    @Test
    public void deadlineTask_InHardDisk() {
        Deadline deadline = new Deadline("do homework",
                LocalDateTime.of(2023, 5, 11, 14, 0));
        assertEquals("D| 0 | do homework | 2023-05-11T14:00", deadline.toStringForFile());
    }

    @Test
    public void markAsDone_InTaskList() {
        Deadline deadline  = new Deadline("go jim",
                LocalDateTime.of(2024, 2, 11, 12, 30));
        deadline.markAsDone();
        assertEquals("[D][X] go jim (by: Feb 11 2024 | 12:30 PM)", deadline.toString());
    }

    @Test
    public void markAsDone_InHardDisk() {
        Deadline deadline  = new Deadline("submit week 3 ip",
                LocalDateTime.of(2024, 2, 2, 23, 59));
        deadline.markAsDone();
        assertEquals("D| 1 | submit week 3 ip | 2024-02-02T23:59", deadline.toStringForFile());
    }
}