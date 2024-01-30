package osiris.formatters;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeFormatters {

    private static DateTimeFormatters instance;

    private DateTimeFormatters() {}

    public static DateTimeFormatters getInstance() {
        if (instance == null) {
            instance = new DateTimeFormatters();
        }
        return instance;
    }

    public LocalDate userInputDateFormatter(String dateStr) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        try {
            return LocalDate.parse(dateStr, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            System.out.println("Failed to parse the date-time string: " + dateStr);
            System.out.println("Please try /by dd-mm-yyyy for a deadline tasks.");
            return null;
        }
    }

    public LocalDateTime userInputDateTimeFormatter(String dateTimeStr) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

        try {
            return LocalDateTime.parse(dateTimeStr, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            System.out.println("Failed to parse the date-time string: " + dateTimeStr);
            System.out.println("Please provide date time range 'dd-MM-yyyy HHmm' format.");
            return null;
        }
    }

    public LocalDateTime[] userInputDateTimeRangeFormatter(String fromDateTimeStr, String toTimeStr) {
        DateTimeFormatter startDateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        DateTimeFormatter endDateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

        try {
            LocalDateTime startDateTime = LocalDateTime.parse(fromDateTimeStr, startDateTimeFormatter);
            LocalDateTime endDateTime = LocalDateTime.parse(toTimeStr, endDateTimeFormatter);
            return new LocalDateTime[]{startDateTime, endDateTime};
        } catch (DateTimeParseException e) {
            System.out.println("Failed to parse the date time range.");
            System.out.println("Please provide date time range in 'dd-MM-yyyy HHmm' format.");
            return null;
        }
    }

    public LocalDate storedDataDateFormatter(String dateStr) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM d yyyy");

        try {
            return LocalDate.parse(dateStr, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            System.out.println("Failed to parse the date-time string: '" + dateStr);
            return null;
        }
    }

    public LocalDateTime storedDataDateTimeFormatter(String dateTimeStr) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM d yyyy h:mm a");

        try {
            return LocalDateTime.parse(dateTimeStr, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            System.out.println("Failed to parse the date-time string: '" + dateTimeStr);
            return null;
        }
    }

    public LocalDateTime[] storedDataDateTimeRangeFormatter(String fromDateTimeStr, String toTimeStr) {
        DateTimeFormatter startDateTimeFormatter = DateTimeFormatter.ofPattern("MMM d yyyy h:mm a");
        DateTimeFormatter endDateTimeFormatter = DateTimeFormatter.ofPattern("MMM d yyyy h:mm a");

        try {
            LocalDateTime startDateTime = LocalDateTime.parse(fromDateTimeStr, startDateTimeFormatter);
            LocalDateTime endDateTime = LocalDateTime.parse(toTimeStr, endDateTimeFormatter);
            return new LocalDateTime[]{startDateTime, endDateTime};
        } catch (DateTimeParseException e) {
            System.out.println("Failed to parse the date time range: " + fromDateTimeStr + " OR " + toTimeStr);
            return null;
        }
    }

}
