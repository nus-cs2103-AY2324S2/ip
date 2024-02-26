package data.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seiki.common.DateTime.DATE_TIME_FORMATTER;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seiki.data.task.EventTask;

public class EventTaskTest {
    @Test
    public void toString_unmarkedEvent_success() {
        EventTask task = new EventTask("test",
                LocalDateTime.parse("2020/02/02 1234", DATE_TIME_FORMATTER),
                LocalDateTime.parse("2020/02/02 2234", DATE_TIME_FORMATTER));
        assertEquals("[E] test (from: 02 Feb 2020 1234 to: 02 Feb 2020 2234)", task.toString());
    }


    @Test
    public void toString_markedEvent_success() {
        EventTask task = new EventTask("test",
                LocalDateTime.parse("2020/02/02 1234", DATE_TIME_FORMATTER),
                LocalDateTime.parse("2020/02/02 2234", DATE_TIME_FORMATTER));
        task.markAsDone();
        assertEquals("[E] test (from: 02 Feb 2020 1234 to: 02 Feb 2020 2234) ✓", task.toString());
    }

    @Test
    public void toFile_unmarkedEvent_success() {
        EventTask task = new EventTask("test",
                LocalDateTime.parse("2020/02/02 1234", DATE_TIME_FORMATTER),
                LocalDateTime.parse("2020/02/02 2234", DATE_TIME_FORMATTER));
        assertEquals("E | 0 | test | 2020/02/02 1234-2020/02/02 2234", task.toFile());
    }

    @Test
    public void toFile_markedEvent_success() {
        EventTask task = new EventTask("test",
                LocalDateTime.parse("2020/02/02 1234", DATE_TIME_FORMATTER),
                LocalDateTime.parse("2020/02/02 2234", DATE_TIME_FORMATTER));
        task.markAsDone();
        assertEquals("E | 1 | test | 2020/02/02 1234-2020/02/02 2234", task.toFile());
    }
}
