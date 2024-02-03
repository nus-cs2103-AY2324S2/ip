package saopig.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void testToString() {
        LocalDateTime deadlineDateTime = LocalDateTime.parse("2020-02-02 18:00",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        assertEquals("[D][ ] return book (by: Sunday, February 2, 2020 at 6:00:00 PM Singapore Standard Time)",
                new Deadline("return book", deadlineDateTime).toString());
    }
}
