package utilities;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import exceptions.WilliamException;

import java.time.LocalDateTime;

/**
 * Test class for the DateAndTimeParser in utilities package
 */
public class DateAndTimeParserTest {
    /**
     * Tests the acceptDateAndTime method with the correct input, no exception thrown
     */
    @Test
    public void acceptDateAndTime_correctDateTime_noException() {
        assertDoesNotThrow(() -> DateAndTimeParser.acceptDateAndTime("12/12/2023 1800"));

        assertDoesNotThrow(() -> DateAndTimeParser.acceptDateAndTime("13/12/2023 1700"));
    }

    /**
     * Tests the acceptDateAndTime method with the wrong input, exception thrown
     */
    @Test
    public void acceptDateAndTime_incorrectDateTime_exceptionThrown() {
        try {
            DateAndTimeParser.acceptDateAndTime("12-12-2023 5pm");
            fail();
        } catch (WilliamException e) {
            assertEquals("The date and time format is invalid. Please try again!", e.getMessage());
        }
    }

    /**
     * Tests the acceptDateAndTime method with the wrong input, exception thrown
     */
    @Test
    public void acceptDateAndTime_invalidDateTime_exceptionThrown() {
        try {
            DateAndTimeParser.acceptDateAndTime("30/02/2024 1200");
            fail();
        } catch (WilliamException e) {
            assertEquals("The date and time format is invalid. Please try again!", e.getMessage());
        }
    }

    /**
     * Tests the checkWhetherToAndFromValid method with the correct input, no exception thrown
     */
    @Test
    public void checkWhetherToAndFromValid_correctToAndFrom_noException() {
        assertDoesNotThrow(() -> DateAndTimeParser.checkWhetherToAndFromValid("11/12/2023 1800",
                "12/12/2023 1800"));
    }

    /**
     * Tests the checkWhetherToAndFromValid method with the wrong input, exception thrown
     */
    @Test
    public void checkWhetherToAndFromValid_fromDateAfterToDate_exceptionThrown() {
        try {
            DateAndTimeParser.checkWhetherToAndFromValid("13/12/2023 1800", "12/12/2023 1800");
            fail();
        } catch (WilliamException e) {
            assertEquals(
                    "The '/from' date and time should be before '/to' date and time. Please try again!",
                    e.getMessage());
        }
    }

    /**
     * Tests the checkWhetherToAndFromValid method with the wrong input, exception thrown
     */
    @Test
    public void checkWhetherToAndFromValid_fromDateEqualToDate_exceptionThrown() {
        try {
            DateAndTimeParser.checkWhetherToAndFromValid("12/12/2023 1800", "12/12/2023 1800");
            fail();
        } catch (WilliamException e) {
            assertEquals(
                    "The '/from' date and time cannot be equal to '/to' date and time. Please try again!",
                    e.getMessage());
        }
    }

    /**
     * Tests the convertStringToDate method with the correct input
     */
    @Test
    public void convertStringToDate() {
        LocalDateTime expectedDate = LocalDateTime.of(2023, 12, 12, 18, 0);
        LocalDateTime actualDate = DateAndTimeParser.convertStringToDate("12/12/2023 1800");
        assertEquals(expectedDate, actualDate);
    }
}
