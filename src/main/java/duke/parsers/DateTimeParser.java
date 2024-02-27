package duke.parsers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

/**
 * Class to parse a DateTime object into a String of different format or vice versa
 */
public class DateTimeParser {

    /**
     * Converts DateTime object to String
     * @param ldt DateTime object to be converted
     * @return a String representation of the DateTime object in a different format.
     */
    public static String dtToString(LocalDate ldt) {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        String formattedDateTime = ldt.format(outputFormatter);
        return formattedDateTime;
    }

    /**
     * COnverts String to DateTime object
     * @param s String to be converted
     * @return DateTime object to be returned to store into a Task.
     */
    public static LocalDate stringToDT(String s) {
        List<String> dateTimeFormats = Arrays.asList(
                "d/MM/yyyy",
                "dd/MM/yyyy",
                "d/M/yyyy",
                "dd/M/yyyy",
                "dd-MM-yyyy",
                "d-MM-yyyy",
                "dd-M-yyyy",
                "d-M-yyyy"
        );
        LocalDate parsedDateTime = LocalDate.now();
        for (String format : dateTimeFormats) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
                parsedDateTime = LocalDate.parse(s, formatter);
                break;  // Exit loop if parsing succeeds
            } catch (Exception e) {
                // Parsing failed for the current format, try the next one
            }
        }
        return parsedDateTime;
    }
}
