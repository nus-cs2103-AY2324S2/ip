package duke.handlers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

/**
 * TimeHandler deals with the conversion of string to LocalDate or related formats.
 */
public class TimeHandler {
    /**
     * TimeHandler constructor.
     */
    public TimeHandler() {
    }

    /**
     * Parses and converts string to LocalDateTime object.
     *
     * @param dateTime String containing date and time.
     * @return dateTime     LocalDateTime object.
     */
    public LocalDateTime parseDateTime(String dateTime) {
        List<String> separators = Arrays.asList("-", "/");
        List<String> dateCombinations = Arrays.asList("dd_MM_yyyy ", "MM_dd_yyyy ", "yyyy_dd_MM ", "yyyy_MM_dd ");
        List<String> timeCombinations = Arrays.asList("HH:mm", "HHmm", "hh:mm a");

        for (String d : dateCombinations) {
            for (String t : timeCombinations) {
                for (String s : separators) {
                    try {
                        String pattern = d.replaceAll("_", s) + t;
                        return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(pattern));
                    } catch (DateTimeParseException dt) {
                        // ...
                    }
                }
            }
        }

        return null;
    }

    /**
     * Parses and converts string to LocalTime object.
     *
     * @param time      String containing time.
     * @return time     LocalTime object.
     */
    public LocalTime parseTime(String time) {
        List<String> timeCombinations = Arrays.asList("HH:mm", "HHmm", "hh:mm a");

        for (String t : timeCombinations) {
            try {
                return LocalTime.parse(time, DateTimeFormatter.ofPattern(t));
            } catch (DateTimeParseException dt) {
                // ...
            }
        }

        return null;
    }

    /**
     * Parses and converts string to LocalDate object.
     * @param date      String containing date.
     * @return date     LocalDate object.
     * */
    public LocalDate parseDate(String date) {
        List<String> separators = Arrays.asList("-", "/");
        List<String> dateCombinations = Arrays.asList("dd_MM_yyyy ", "MM_dd_yyyy ", "yyyy_dd_MM ", "yyyy_MM_dd ");

        for (String d : dateCombinations) {
            for (String s : separators) {
                try {
                    String pattern = d.replaceAll("_", s);
                    return LocalDate.parse(date, DateTimeFormatter.ofPattern(pattern));
                } catch (DateTimeParseException dt) {
                    // ...
                }
            }
        }

        return null;
    }
}
