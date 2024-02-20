package duke.utils;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DukeDateFormaterTest {
    @Test
    public void dukeDateFormater_stringToDate_success() {
        DukeDateFormater formater = new DukeDateFormater();
        // Format date success - the string is valid
        LocalDate date = formater.stringToDate("2019-12-01");
        Assertions.assertEquals("2019-12-01", date.toString());
    }

    @Test
    public void dukeDateFormater_stringToDate_exceptionThrown1() {
        DukeDateFormater formater = new DukeDateFormater();
        // Format date fail - the string is empty
        Assertions.assertThrows(DateTimeParseException.class, () -> {
            LocalDate date = formater.stringToDate("");
        });
    }

    @Test
    public void dukeDateFormater_stringToDate_exceptionThrown2() {
        DukeDateFormater formater = new DukeDateFormater();
        // Format date fail - the string exceeds the valid date range.
        Assertions.assertThrows(DateTimeParseException.class, () -> {
            LocalDate date = formater.stringToDate("2019-12-99");
        });
    }

    @Test
    public void dukeDateFormater_stringToDate_exceptionThrown3() {
        DukeDateFormater formater = new DukeDateFormater();
        // Format date fail - the string is not in supported date format
        Assertions.assertThrows(DateTimeParseException.class, () -> {
            LocalDate date = formater.stringToDate("2019/12/01");
        });
    }

    @Test
    public void dukeDateFormater_stringToDate_exceptionThrown4() {
        DukeDateFormater formater = new DukeDateFormater();
        // Format date fail - the string contains non-numeric characters.
        Assertions.assertThrows(DateTimeParseException.class, () -> {
            LocalDate date = formater.stringToDate("abca-bc-ab");
        });
    }

}
