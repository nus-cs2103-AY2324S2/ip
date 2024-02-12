package bob.task;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void toStorageFormat_notDone_success() {
        assertEquals("D | false | a | 2024-02-12T19:37:00", new Deadline("a",
                LocalDateTime.of(2024, Month.FEBRUARY, 12, 19, 37, 0))
                .toStorageFormat());
    }

    @Test
    public void toStorageFormat_done_success() {
        Deadline deadline = new Deadline("a",
                LocalDateTime.of(2024, Month.FEBRUARY, 12, 19, 37, 0));
        deadline.setDone(true);
        assertEquals("D | true | a | 2024-02-12T19:37:00", deadline.toStorageFormat());
    }
}
