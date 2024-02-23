package alfred.util;

import alfred.task.TaskException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

/**
 * Manages the parsing and formatting of date and time strings.
 * This class supports multiple input formats and converts them into a standard output format.
 */
public class TimeManager {
    /**
     * Parses a date and time string from various known formats into a standardized format.
     * If the input does not match any known format, it returns the input as is.
     *
     * @param input The date and time string to be parsed.
     * @return A date and time string formatted in the standard output format
     *         "dd MMMM yyyy, h:mm a" if the input matches any of the known formats;
     *         otherwise, returns the original input string.
     */
    public static String parseTime(String input) {
        List<DateTimeFormatter> formatters = Arrays.asList(
                DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"),
                DateTimeFormatter.ofPattern("M/d/yyyy hh:mm a"),
                DateTimeFormatter.ofPattern("dd MMMM yyyy, h:mm a")
        );

        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy, h:mm a");

        String timeResult = input;
        for (DateTimeFormatter formatter : formatters) {
            try {
                LocalDateTime currTime = LocalDateTime.parse(input, formatter);
                timeResult = currTime.format(outputFormatter);
                break;
            } catch (DateTimeParseException e) {
                timeResult = input;
            }

        }

        return timeResult;
    }

    public static LocalDateTime convertTime(String input) throws TaskException{
        List<DateTimeFormatter> formatters = Arrays.asList(
                DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"),
                DateTimeFormatter.ofPattern("M/d/yyyy hh:mm a"),
                DateTimeFormatter.ofPattern("dd MMMM yyyy, h:mm a")
        );
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy, h:mm a");

        for (DateTimeFormatter formatter : formatters) {
            try {
                LocalDateTime currTime = LocalDateTime.parse(input, formatter);
                currTime.format(outputFormatter);
                return currTime;
            } catch (DateTimeParseException e) {
                continue;
            }
        }
        throw new TaskException("Cannot convert time");
    }

    public static String addDays(String time, int days) throws TaskException {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy, h:mm a");
        LocalDateTime currTime = TimeManager.convertTime(time);
        LocalDateTime newTime;
        newTime = currTime.plusDays(days);
        return newTime.format(outputFormatter);
    }
}
