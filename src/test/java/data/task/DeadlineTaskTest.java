package data.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seiki.common.DateTime.DATE_TIME_FORMATTER;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seiki.data.task.DeadlineTask;


public class DeadlineTaskTest {

    @Test
    public void toString_unmarkedDeadline_success() {
        DeadlineTask task = new DeadlineTask("test", LocalDateTime.parse("2020/02/02 1234", DATE_TIME_FORMATTER));
        assertEquals("[D] test (by: 02 Feb 2020 1234)", task.toString());
    }


    @Test
    public void toString_markedDeadline_success() {
        DeadlineTask task = new DeadlineTask("test", LocalDateTime.parse("2020/02/02 1234", DATE_TIME_FORMATTER));
        task.markAsDone();
        assertEquals("[D] test (by: 02 Feb 2020 1234) ✓", task.toString());
    }

    @Test
    public void toFile_unmarkedDeadline_success() {
        DeadlineTask task = new DeadlineTask("test", LocalDateTime.parse("2020/02/02 1234", DATE_TIME_FORMATTER));
        assertEquals("D | 0 | test | 2020/02/02 1234", task.toFile());
    }

    @Test
    public void toFile_markedDeadline_success() {
        DeadlineTask task = new DeadlineTask("test", LocalDateTime.parse("2020/02/02 1234", DATE_TIME_FORMATTER));
        task.markAsDone();
        assertEquals("D | 1 | test | 2020/02/02 1234", task.toFile());
    }
}
