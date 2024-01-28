package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateFormatter {
    private static final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
    private static final DateTimeFormatter outputDateTimeFormat = DateTimeFormatter.ofPattern("MMM d yyyy h:mm a");

    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d/MM/yyyy");
    private static final DateTimeFormatter outputDateFormat = DateTimeFormatter.ofPattern("MMM d yyyy");

    private static final DateTimeFormatter altDateFormat = DateTimeFormatter.ofPattern("yyyy/MM/d");
    private static final DateTimeFormatter altOutputDateFormat = DateTimeFormatter.ofPattern("MMM d yyyy");

    public DateFormatter() {
    }

    private boolean isValidDateTime(String dateStr) {
        try {
            LocalDateTime.parse(dateStr, timeFormat);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private boolean isValidDate(String dateStr) {
        try {
            LocalDate.parse(dateStr, dateFormat);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private boolean isValidAltDate(String dateStr) {
        try {
            LocalDate.parse(dateStr, altDateFormat);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public String convertedDate(String dateStr) {
        if (isValidDateTime(dateStr)) {
            LocalDateTime date = LocalDateTime.parse(dateStr, timeFormat);
            return date.format(outputDateTimeFormat);
        } else if (isValidDate(dateStr)) {
            LocalDate date = LocalDate.parse(dateStr, dateFormat);
            return date.format(outputDateFormat);
        } else if (isValidAltDate(dateStr)) {
            LocalDate date = LocalDate.parse(dateStr, altDateFormat);
            return date.format(altOutputDateFormat);
        }
        return dateStr;
    }
}
