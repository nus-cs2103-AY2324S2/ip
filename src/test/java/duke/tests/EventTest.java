package duke.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import duke.tasks.Events;

public class EventTest {
    @Test
    public void eventToStringTest() {
        LocalDateTime dateTime1 = LocalDateTime.of(2020, 1, 1, 15, 0);
        LocalDateTime dateTime2 = LocalDateTime.of(2020, 1, 1, 16, 0);
        assertEquals("[E][ ] Meet Prof Damith(from: 1/1/2020 1500hrs, to: 1/1/2020 1600hrs)",
                    new Events("Meet Prof Damith", dateTime1, dateTime2).toString());
    }
}
