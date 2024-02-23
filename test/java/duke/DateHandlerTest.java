package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;



public class DateHandlerTest {

    @Test
    public void testValidDate() {
        String invalidDate1 = "34/02/2024 1000";
        String invalidDate2 = "10/13/2024 2505";
        String validDate1 = "23/02/2024 2359";
        String validDate2 = "01/01/2024 0000";
        assertEquals(true, DateHandler.isValidDate(validDate1));
        assertEquals(false, DateHandler.isValidDate(invalidDate1));
    }

    @Test
    public void storageConversion() {
        String validDate1 = "23/02/2024 2359";
        String validDate2 = "01/01/2024 0000";

        // Convert date strings to date time objects
        LocalDateTime validDate1 = DateHandler.inputStringDateTime(validDate1);
        LocalDateTime validDate2 = DateHandler.inputStringDateTime(validDate2);

        // Convert LocalDateTime objects to string format for storage 
        String validDate1Storage = DateHandler.objDateTime(validDate1);
        String validDate2Storage = DateHandler.objDateTime(validDate2);

        // return the db string format when printing
        assertEquals("23 February 2023 23:59", validDate1Storage);
        assertEquals("01 January 2024 00:00", validDate2Storage);
    }
}
