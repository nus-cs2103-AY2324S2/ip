package duke.Parsers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
public class DateTimeParser {

    public static String dtToString(LocalDate ldt) {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        String formattedDateTime = ldt.format(outputFormatter);
        return formattedDateTime;
    }

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
