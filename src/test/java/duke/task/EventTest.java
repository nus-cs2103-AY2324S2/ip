package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import org.junit.jupiter.api.Test;

import duke.exception.DateFormatException;

public class EventTest {
    @Test
    public void event_constructor_success() throws Exception {
        DateTimeFormatter returnFormat = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        LocalDateTime t1 = LocalDateTime.of(2024, 2, 3, 16, 00);
        LocalDateTime t2 = LocalDateTime.of(2024, 2, 3, 18, 00);
        String success = "[E] [ ] project meeting (" + returnFormat.format(t1) + " to " + returnFormat.format(t2) + ")";
        assertEquals(success, new Event("project meeting", "3/2/2024 1600", "3/2/2024 1800").toString());
    }

    @Test
    public void eventConstructor_invalidDate_exceptionThrown() {
        String errorMessage = "Please check the format of your date input! Accepted format: DD/MM/YYYY TTTT";
        try {
            new Event("project meeting", "3.2.24 4pm", "3.2.24 pm");
            fail();
        } catch (DateFormatException e) {
            assertEquals(errorMessage, e.getMessage());
        }
    }
}
