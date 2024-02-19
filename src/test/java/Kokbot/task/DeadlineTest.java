package Kokbot.task;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void toString_normalInput_success() {
        LocalDateTime dateTime = LocalDateTime.parse("2021-08-24T18:00");
        assertEquals("[D][ ] read book (by: Aug 24 2021 6:00PM)",
                new Deadline("read book", dateTime).toString());
    }

    @Test
    public void toFileString_normalInput_success() {
        LocalDateTime dateTime = LocalDateTime.parse("2021-08-24T18:00");
        assertEquals("D, ,read book,2021-08-24T18:00",
                new Deadline ("read book", dateTime).toFileString());
    }
}
