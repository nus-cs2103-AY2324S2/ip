package duke.utils;

import duke.utils.DukeDateFormater;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DukeDateFormaterTest {
    @Test
    public void testStringToDate() {
        DukeDateFormater formater = new DukeDateFormater();
        // Format date success - the string is valid
        LocalDate date = formater.stringToDate("2019-12-01");
        Assertions.assertEquals("2019-12-01", date.toString());

        // Format date fail - the string is not in valid date format
        Assertions.assertThrows(DateTimeParseException.class, ()-> {
            LocalDate date2 = formater.stringToDate("2019-12-99");
        });

        // Format date fail - the string is not in valid date format
        Assertions.assertThrows(DateTimeParseException.class, ()-> {
            LocalDate date2 = formater.stringToDate("2019/12/01");
        });

        // Format date fail - the string is not in valid date format
        Assertions.assertThrows(DateTimeParseException.class, ()-> {
            LocalDate date2 = formater.stringToDate("abca-bc-ab");
        });

    }
}
