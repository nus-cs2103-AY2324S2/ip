package someboty.managers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import someboty.exceptions.InputException;

public class DateManagerTest {
    
    public DateManagerTest() {
    }

    @Test
    public void TestValidDates() {
        String[] ValidDates = {
            "2020-01-31 2259",  // yyyy-MM-dd HHmm
            "12-12-1999 2300",  // dd-MM-yyyy HHmm
            "12-13-6402",       // MM-dd-yyyy without time
            "02/28/9999 0000",  // MM/dd/yyyy HHmm
            "0001/12/30",  // yyyy/MM/dd without time
        };

        String[] expectedDates = {
            "31 Jan 2020 22:59",
            "12 Dec 1999 23:00",
            "13 Dec 6402 23:59",
            "28 Feb 9999 00:00",
            "30 Dec 0001 23:59",
        };

        String actual;
        for (int i = 0; i < expectedDates.length; i++) {
            try {
                actual = DateManager.printDate(DateManager.parseDate(ValidDates[i]));
            } catch (InputException e) {
                actual = e.getMessage();
            }

            assertEquals(expectedDates[i], actual);
        }
    }

    @Test
    public void TestInvalidDates() {
        String[] invalidDates = {
            "2020-12-32 2259",  // invalid day number
            "12-12-1999 2460",  // invalid hour and minutes
            "13-6402",          // missing day/month
            "02/28/9999 4pm",   // use 12-hour time
            "0001/12/3",        // incorrect length of days
            "2020/1/31",        // incorrect length of months
            "202/12/3",         // incorrect length of years
        };

        String expected = "Sorry, I can't recognize that date and time format of yours.";
        String actual;

        for (String date : invalidDates) {
            try {
                actual = DateManager.printDate(DateManager.parseDate(date));
            } catch (InputException e) {
                actual = e.getMessage();
            }

            assertEquals(expected, actual);
        }
    }
}
