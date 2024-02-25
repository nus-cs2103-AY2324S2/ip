import java.time.LocalDateTime;

import kokbot.task.Deadline;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void toString_normalInput_success() {
        LocalDateTime dateTime = LocalDateTime.parse("2021-08-24T18:00");
        Assertions.assertEquals("[D][ ] read book (by: Aug 24 2021 6:00PM)",
                new Deadline("read book", dateTime).toString());
    }

    @Test
    public void toString_noDescription_success() {
        LocalDateTime dateTime = LocalDateTime.parse("2021-08-24T18:00");
        assertEquals("[D][ ]  (by: Aug 24 2021 6:00PM)",
                new Deadline("", dateTime).toString());
    }

    @Test
    public void toFileString_normalInput_success() {
        LocalDateTime dateTime = LocalDateTime.parse("2021-08-24T18:00");
        assertEquals("D, ,read book,2021-08-24T18:00",
                new Deadline ("read book", dateTime).toFileString());
    }

    @Test
    public void toFileString_noDescription_success() {
        LocalDateTime dateTime = LocalDateTime.parse("2021-08-24T18:00");
        assertEquals("D, ,,2021-08-24T18:00",
                new Deadline ("", dateTime).toFileString());
    }
}
