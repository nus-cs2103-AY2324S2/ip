package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Utils {
    public static LocalDate convertStringToDateTime(String datetime) throws DukeException {
        int lenDatetime = datetime.length();
        LocalDate localDate;
        // date format: 2019-12-01
        if (lenDatetime == 10) {
            localDate = LocalDate.parse(datetime, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } else {
            throw new DukeException("Invalid datetime format!");
        }
        return localDate;
    }

    public static String printTime(LocalDate datetime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return datetime.format(formatter);
    }

    public static String convertDateTimeToString(LocalDate datetime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return datetime.format(formatter);
    }
}
