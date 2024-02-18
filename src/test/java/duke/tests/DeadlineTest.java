package duke.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import duke.tasks.Deadlines;

public class DeadlineTest {
    @Test
    public void deadlineToStringTest() {
        LocalDateTime dateTime = LocalDateTime.of(2020, 1, 1, 19, 0);
        assertEquals("[D][ ] Find Bill(by: 1/1/2020 1900hrs)", new Deadlines("Find Bill", dateTime).toString());
    }
}
