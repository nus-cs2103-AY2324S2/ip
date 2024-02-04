package Duke.activityAndUtility;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The {@code DateTimeFormat} class provides static utility methods for parsing {@link LocalDate} and {@link LocalTime}
 * from strings. It supports multiple date formats for flexibility in parsing dates and a specific time format.
 */
class DateTimeFormat {
    /**
     * Parses a {@link LocalDate} from the provided input string. The method supports two date formats: "yyyy-MM-dd" and "dd-MM-yyyy".
     * It attempts to parse the date using the first format and falls back to the second format if the first attempt fails.
     *
     * @param input The input string containing the date to be parsed. The date is expected to be the first part of the input,
     *              separated by a space from any other information.
     * @return The parsed {@link LocalDate}, or {@code null} if neither format can parse the input successfully.
     */
    static public LocalDate getDate(String input) {
        String[] dateString = input.split(" ", 2);
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        try {
            return LocalDate.parse(dateString[0], formatter1);
        } catch (DateTimeParseException ignored) {
        }
        try {
            return LocalDate.parse(dateString[0], formatter2);
        } catch (DateTimeParseException format) {
            return null;
        }
    }

    /**
     * Parses a {@link LocalTime} from the provided input string. The method supports the time format "HHmm",
     * expecting the time to be the second part of the input, separated by a space from any other information.
     *
     * @param input The input string containing the time to be parsed. The time is expected to be the second part of the input,
     *              separated by a space from the date part.
     * @return The parsed {@link LocalTime}, or {@code null} if the format cannot parse the input successfully.
     */
    static public LocalTime getTime(String input) {
        String[] timeString = input.split(" ", 2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
        try {
            return LocalTime.parse(timeString[1], formatter);
        } catch (DateTimeParseException format) {
            return null;
        }
    }
}
