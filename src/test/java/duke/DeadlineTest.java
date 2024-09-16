package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void toStringTest() {
        assertEquals(new Deadline("return book", LocalDateTime.parse("2021-08-25T18:00")).toString(),
            "[D][ ] return book (by: Aug 25 2021 18:00)");
    }
}
