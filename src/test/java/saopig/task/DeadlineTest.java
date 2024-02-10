package saopig.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void testToString() {
        LocalDateTime deadlineDateTime = LocalDateTime.parse("2020-02-02 18:00",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        assert new Deadline("return book", deadlineDateTime).toString().contains(
                "[D][ ] return book "
        );
    }
}
