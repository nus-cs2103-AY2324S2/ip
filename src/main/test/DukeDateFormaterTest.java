import org.junit.Assert;
import org.junit.Test;
import duke.utils.DukeDateFormater;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DukeDateFormaterTest {
    @Test
    public void testFormatDate() {
        DukeDateFormater formater = new DukeDateFormater();
        // Format date success - the string is valid
        LocalDate date = formater.stringToDate("2019-12-01");
        Assert.assertEquals("2019-12-01", date.toString());

        // Format date fail - the string is not in valid date format
        Assert.assertThrows(DateTimeParseException.class, ()-> {
            LocalDate date2 = formater.stringToDate("2019-12-99");
        });

        // Format date fail - the string is not in valid date format
        Assert.assertThrows(DateTimeParseException.class, ()-> {
            LocalDate date2 = formater.stringToDate("2019/12/01");
        });

        // Format date fail - the string is not in valid date format
        Assert.assertThrows(DateTimeParseException.class, ()-> {
            LocalDate date2 = formater.stringToDate("abca-bc-ab");
        });

    }
}
