package duke.tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import duke.tasks.Deadlines;


public class DeadlineTest {
    @Test
    public void Deadline_toString(){
        LocalDateTime dateTime = LocalDateTime.of(2020, 1, 1, 19, 0);
        assertEquals("[D][ ] Find Bill(by: 1/1/2020 1900hrs)", new Deadlines("Find Bill", dateTime).toString());
    }
}
