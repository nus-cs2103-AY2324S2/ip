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

    /*

    @Test
    public void intDivision_nonZeroDivisor_success() throws Exception {
        // normal division results in an integer answer 2
        assertEquals(2, new IntPair(4, 2).intDivision());

        // normal division results in a decimal answer 1.9
        assertEquals(1, new IntPair(19, 10).intDivision());

        // dividend is zero but devisor is not
        assertEquals(0, new IntPair(0, 5).intDivision());
    }

    @Test
    public void intDivision_zeroDivisor_exceptionThrown() {
        try {
            assertEquals(0, new IntPair(1, 0).intDivision());
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("Divisor is zero", e.getMessage());
        }
    }

    @Test
    public void testStringConversion() {
        assertEquals("4,7", new IntPair(4, 7).toString());
    }
     */
}
