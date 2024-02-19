package chaterpillar.datetime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import chaterpillar.exceptions.ChaterpillarException;

public class DateTimeTest {
    @Test
    public void isSameDay_sameDay_success() throws ChaterpillarException {
        // same date with same input string to be parsed
        assertTrue(new DateTime("09/02/2024").isSameDay(new DateTime("9/02/2024")));

        // same date with different input string
        assertTrue(new DateTime("09/02/2024").isSameDay(new DateTime("9/Feb/2024 2 PM")));
        assertTrue(new DateTime("09/02/2024").isSameDay(new DateTime("9 Feb")));
        assertTrue(new DateTime("09/02/2024 1400").isSameDay(new DateTime("9 Feb 2 PM")));

        // different date
        assertFalse(new DateTime("09/02/2024").isSameDay(new DateTime("10/02/2023")));
    }

    @Test
    public void dateTime_invalidInput_exceptionThrown() {
        try {
            new DateTime("invalid date time format");
        } catch (ChaterpillarException e) {
            assertEquals("Error in parsing string for date/time.\n"
                         + "I accept quite a number of common date format, \n"
                         + "but here is one you can use: DD/MM/YYY HH:MM", e.getMessage());
        }
    }
}
