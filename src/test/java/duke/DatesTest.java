package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;



public class DatesTest {

    @Test
    public void testIsValidInputDate() {
        String invalidInputDate1 = "01/13/2023 1200";
        String invalidInputDate2 = "01/13/2023 2401";
        String validInputDate1 = "15/01/2023 1430";
        String validInputDate2 = "01/01/2025 2330";
        assertEquals(true, Dates.isValidInputDate(validInputDate1));
        assertEquals(true, Dates.isValidInputDate(validInputDate2));
        assertEquals(false, Dates.isValidInputDate(invalidInputDate1));
        assertEquals(false, Dates.isValidInputDate(invalidInputDate2));

    }

    @Test
    public void testConvMethods() {
        String validInputDate1 = "15/01/2023 1430";
        String validInputDate2 = "01/01/2025 2330";
        // Convert valid date strings to LocalDateTime objects
        LocalDateTime validDate1 = Dates.inputStr2DateTime(validInputDate1);
        LocalDateTime validDate2 = Dates.inputStr2DateTime(validInputDate2);

        // Convert LocalDateTime objects to string for storage in database file
        String validDate1Str = Dates.dateTime2DbStr(validDate1);
        String validDate2Str = Dates.dateTime2DbStr(validDate2);

        // return the db string format when printing
        assertEquals("15 January 2023 14:30", validDate1Str);
        assertEquals("01 January 2025 23:30", validDate2Str);
    }
}
