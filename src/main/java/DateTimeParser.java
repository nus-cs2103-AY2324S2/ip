import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeParser {
    private DateTimeParser() {

    }
    public static LocalDateTime parseDateTime(String by) throws DukeExceptions {
        String[] splitBy = by.split(" ");

        if (splitBy.length != 2) {
            throw new DukeExceptions("Please include date and time, in this format, yyyy-mm-dd and hhmm.");
        }

        String[] splitDayMonthYear = new String[3];
        int day = 0;
        int month = 0;
        int year = 0;
        if (splitBy[0].contains("/")) {
            splitDayMonthYear = splitBy[0].split("/");
        } else if (splitBy[0].contains("-")) {
            splitDayMonthYear = splitBy[0].split("-");
        } else if (splitBy[1].contains("/")) {
            splitDayMonthYear = splitBy[1].split("/");
        } else if (splitBy[1].contains("-")) {
            splitDayMonthYear = splitBy[1].split("-");
        } else {
            throw new DukeExceptions("Please include date and time, in thi format, yyyy-mm-dd hhmm");
        }

        month = Integer.parseInt(splitDayMonthYear[1]);

        if (splitDayMonthYear[0].length() <= 2) {
            day = Integer.parseInt(splitDayMonthYear[0]);
            year = Integer.parseInt(splitDayMonthYear[2]);
        } else {
            day = Integer.parseInt(splitDayMonthYear[2]);
            year = Integer.parseInt(splitDayMonthYear[0]);
        }

        int hour = Integer.parseInt(splitBy[1])/100;
        int min = Integer.parseInt(splitBy[1]) % 100;

        LocalDateTime ldt = LocalDateTime.of(year, month, day, hour, min);
        return ldt;
    }

    public static String toString(LocalDateTime ldt) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM dd yyyy hhmm");
        return ldt.format(dtf);
    }
}
