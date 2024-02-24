package yue.tasks;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTaskTest {

    @Test
    public void testFormatDateTime() {
        LocalDateTime startTime = LocalDateTime.of(2024, 1, 30, 15, 30);
        LocalDateTime endTime = LocalDateTime.of(2024, 1, 30, 16, 30);
        EventTask task = new EventTask("Test Event", "2024-01-30 1530", "2024-01-30 1630");


        String formattedStartTime = task.formatDateTime(startTime);
        String formattedEndTime = task.formatDateTime(endTime);

        assertEquals("Jan 30 2024 15:30", formattedStartTime);
        assertEquals("Jan 30 2024 16:30", formattedEndTime);
    }
}
