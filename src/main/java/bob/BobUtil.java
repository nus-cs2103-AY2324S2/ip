package bob;

import java.time.LocalDateTime;

/**
 * Utility functions that are used by the chat bot.
 */
public class BobUtil {

    public static LocalDateTime convertToLocalDateTime(String[] args, int hour, int minute)
            throws BobException.InvalidDateTimeFormat {

        String[] date = args[0].split("\\/");
        if (date.length != 3) {
            throw new BobException.InvalidDateTimeFormat(
                    "Date specified is of the wrong format (expects dd/mm/yyyy i.e 01/01/1990).");
        }

        LocalDateTime dateTime;

        int year = 0;
        int month = 0;
        int day = 0;

        try {
            year = Integer.parseInt(date[2]);
            month = Integer.parseInt(date[1]);
            day = Integer.parseInt(date[0]);

            dateTime = LocalDateTime.of(year, month, day, hour, minute);

        } catch (Exception e) {
            throw new BobException.InvalidDateTimeFormat(
                    "Date specified is of the wrong format (expects dd/mm/yyyy i.e 01/01/1990).");
        }
        return dateTime;
    }
}
