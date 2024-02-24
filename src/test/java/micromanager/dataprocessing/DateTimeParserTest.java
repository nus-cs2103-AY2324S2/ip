package micromanager.dataprocessing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import micromanager.exceptions.DukeException;


public class DateTimeParserTest {
    @Test
    public void testParseValidDate() {
        String validDate = "2024-12-31";
        try {
            LocalDate result = DateTimeParser.parse(validDate);
            assertNotNull(result);
            assertEquals(LocalDate.parse(validDate), result);
        } catch (DukeException e) {
            fail("Unexpected DukeException thrown for a valid date: " + e.getMessage());
        }
    }

    @Test
    public void testParseInvalidDateFormat() {
        String invalidDate = "31/12/2024"; // Invalid format
        try {
            DateTimeParser.parse(invalidDate);
            fail("Expected DukeException to be thrown due to invalid date format.");
        } catch (DukeException e) {
            assertEquals("OOPS!!! deadline should be in the format: yyyy-mm-dd.\n"
                    + "Please check if your date is valid", e.getMessage());
        }
    }

    @Test
    public void testParsePastDate() {
        String pastDate = "2020-01-01"; // A date in the past
        try {
            DateTimeParser.parse(pastDate);
            fail("Expected DukeException to be thrown for a past date.");
        } catch (DukeException e) {
            assertEquals("OOPS!!! The date you provided is already passed.", e.getMessage());
        }
    }
}
