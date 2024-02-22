package plato.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import plato.PlatoException;

/**
 * Handles parsing and identifying DateTime in String Commands
 */
public class DateHandler {
    //Inspired from: https://www.baeldung.com/java-date-regular-expressions
    private static final Pattern PATTERN_DATE =
        Pattern.compile("([a-zA-Z]+)?\\s?(?<d>\\d{1,2})[-/](?<m>\\d{1,2})[-/](?<y>\\d{2,4})\\s?([a-zA-Z]+)?");
    private static final Pattern TIME_PATTERN =
        Pattern.compile("(.+)?\\s?(?<time>(\\d{2}:?\\d{2}))(?<indicator>(?i)\\s?[ap]m)?\\s?(.+)?");
    private static final DateTimeFormatter FORMAT_DATE = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    /**
     * Checks the given string if it contains a valid date to convert into LocalDate.
     *
     * @param testDate String to test.
     * @return An Optional that contains the LocalDate if it exists.
     * @throws PlatoException Invalid Date format .
     */
    public static Optional<LocalDate> checkDate(String testDate) throws PlatoException {
        Matcher match = PATTERN_DATE.matcher(testDate);
        if (!match.find()) {
            return Optional.empty();
        }

        String d = match.group("d");
        String m = match.group("m");
        String y = match.group("y");


        if (y.length() == 3) {
            throw new PlatoException("dateError");
        } else if (y.length() == 2) {
            y = "20" + y;
        }
        int date = Integer.parseInt(d);
        int month = Integer.parseInt(m);
        if (!(0 < date && date <= 31) || !(0 < month && month <= 12)) {
            throw new PlatoException("dateOutOfRangeError");
        }
        int year = Integer.parseInt(y);
        LocalDate convert = LocalDate.of(year, month, date);
        return Optional.of(convert);
    }

    /**
     * Checks if the given String has a valid time to convert into LocalTime.
     *
     * @param testTime String to test.
     * @return An Optional that contains LocalTime if it exists.
     */
    public static Optional<LocalTime> checkTime(String testTime) throws PlatoException {
        //Remove the date so that it is easier to check for the time, and prevent conflicts
        Matcher removeDate = PATTERN_DATE.matcher(testTime);
        if (removeDate.find()) {
            testTime = testTime.replaceAll(removeDate.group(), "");
        }

        Matcher match = TIME_PATTERN.matcher(testTime);
        Matcher am = Pattern.compile("(?i)[ap]m").matcher(testTime);

        if (!match.matches()) {
            return Optional.empty();
        }

        String time = match.group("time");
        String[] data = time.replaceAll("[ :]", "").split("(?<=\\G\\d{2})");
        int hour = Integer.parseInt(data[0]);
        int minutes = Integer.parseInt(data[1]);

        checkValidTime(hour, minutes);
        hour = handleTwelveHour(am, hour, match);

        //24-hour time format
        return Optional.of(LocalTime.of(hour, minutes));


    }

    private static void checkValidTime(int hour, int minutes) throws PlatoException {
        if (hour < 0 || hour > 23) {
            throw new PlatoException("timeError");
        }
        if (minutes < 0 || minutes > 59) {
            throw new PlatoException("timeError");
        }
    }

    private static int handleTwelveHour(Matcher am, int hour, Matcher match) throws PlatoException {
        if (am.find()) {

            if (hour > 12) {
                throw new PlatoException("timeError");
            }

            String indicator = match.group("indicator").toLowerCase();
            if (indicator.equals("pm")) {
                if (hour < 12) {
                    hour += 12;
                }
            } else {
                //Treat 12am as 24 hour variant
                if (hour == 12) {
                    hour = 0;
                }
            }
        }
        return hour;
    }

    /**
     * Formats a LocalDateTime object into save format.
     *
     * @param date LocalDateTime objects to convert.
     * @return A formatted string of the LocalDateTime object.
     */
    public static String formatDate(LocalDateTime date) {
        return date.format(FORMAT_DATE);
    }


}
