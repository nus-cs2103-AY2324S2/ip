package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void getData_success() {
        LocalDateTime testFrom = LocalDateTime.of(2024, 2, 9, 18, 00);
        LocalDateTime testTo = LocalDateTime.of(2024, 2, 10, 9, 00);
        Event event = new Event("Test Event", testFrom, testTo);
        String expectedData = "E | 0 | Test Event | 09/02/2024 1800 | 10/02/2024 0900";
        assertEquals(event.getData(), expectedData);
    }
}
