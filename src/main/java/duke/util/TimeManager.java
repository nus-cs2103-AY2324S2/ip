package duke.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

public class TimeManager {
    public static String parseTime(String input) {
        List<DateTimeFormatter> formatters = Arrays.asList(
                DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),
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
}
