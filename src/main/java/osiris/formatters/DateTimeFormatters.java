package osiris.formatters;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A singleton class for date and time formatters used in the application.
 */
public class DateTimeFormatters {

    /** A singleton DateTimeFormatters instance. */
    private static DateTimeFormatters instance;

    /** Private constructor to initialise a DateTimeFormatters instance. */
    private DateTimeFormatters() {}

    /**
     * Returns the singleton instance of the DateTimeFormatters class.
     *
     * @return The DateTimeFormatters instance.
     */
    public static DateTimeFormatters getInstance() {
        if (instance == null) {
            instance = new DateTimeFormatters();
        }
        return instance;
    }

    /**
     * Formats the user input string to a LocalDate object.
     *
     * @param dateStr The date string provided by the user.
     * @return The LocalDate object parsed from the string.
     */
    public LocalDate formatUserInputDate(String dateStr) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        try {
            return LocalDate.parse(dateStr, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            System.out.println("Failed to parse the date-time string: " + dateStr);
            System.out.println("Please try /by dd-mm-yyyy for a deadline tasks.");
            return null;
        }
    }

    /**
     * Formats the user input string to a LocalDateTime object.
     *
     * @param dateTimeStr The date and time string provided by the user.
     * @return The LocalDateTime object parsed from the string.
     */
    public LocalDateTime formatUserInputDateTime(String dateTimeStr) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

        try {
            return LocalDateTime.parse(dateTimeStr, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            System.out.println("Failed to parse the date-time string: " + dateTimeStr);
            System.out.println("Please provide date time range 'dd-MM-yyyy HHmm' format.");
            return null;
        }
    }

    /**
     * Formats the user input strings to an array of LocalDateTime objects representing a range.
     *
     * @param fromDateTimeStr The starting date and time string.
     * @param toDateTimeStr       The ending date and time string.
     * @return An array of LocalDateTime objects representing the date time range.
     */
    public LocalDateTime[] formateUserInputDateTimeRange(String fromDateTimeStr, String toDateTimeStr) {
        DateTimeFormatter startDateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        DateTimeFormatter endDateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

        try {
            LocalDateTime startDateTime = LocalDateTime.parse(fromDateTimeStr, startDateTimeFormatter);
            LocalDateTime endDateTime = LocalDateTime.parse(toDateTimeStr, endDateTimeFormatter);
            return new LocalDateTime[]{startDateTime, endDateTime};
        } catch (DateTimeParseException e) {
            System.out.println("Failed to parse the date time range.");
            System.out.println("Please provide date time range in 'dd-MM-yyyy HHmm' format.");
            return null;
        }
    }

    /**
     * Formats the stored date string to a LocalDate object.
     *
     * @param dateStr The stored date string.
     * @return The LocalDate object parsed from the string.
     */
    public LocalDate formatStoredDate(String dateStr) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM d yyyy");

        try {
            return LocalDate.parse(dateStr, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            System.out.println("Failed to parse the date-time string: '" + dateStr);
            return null;
        }
    }

    /**
     * Formats the stored date and time string to a LocalDateTime object.
     *
     * @param dateTimeStr The stored date and time string.
     * @return The LocalDateTime object parsed from the string.
     */
    public LocalDateTime formatStoredDateTime(String dateTimeStr) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM d yyyy h:mm a");

        try {
            return LocalDateTime.parse(dateTimeStr, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            System.out.println("Failed to parse the date-time string: '" + dateTimeStr);
            return null;
        }
    }

    /**
     * Formats the stored date time range strings to an array of LocalDateTime objects.
     *
     * @param fromDateTimeStr The starting date and time string.
     * @param toDateTimeStr       The ending date and time string.
     * @return An array of LocalDateTime objects representing the date time range.
     */
    public LocalDateTime[] formatStoredDateTimeRange(String fromDateTimeStr, String toDateTimeStr) {
        DateTimeFormatter startDateTimeFormatter = DateTimeFormatter.ofPattern("MMM d yyyy h:mm a");
        DateTimeFormatter endDateTimeFormatter = DateTimeFormatter.ofPattern("MMM d yyyy h:mm a");

        try {
            LocalDateTime startDateTime = LocalDateTime.parse(fromDateTimeStr, startDateTimeFormatter);
            LocalDateTime endDateTime = LocalDateTime.parse(toDateTimeStr, endDateTimeFormatter);
            return new LocalDateTime[]{startDateTime, endDateTime};
        } catch (DateTimeParseException e) {
            System.out.println("Failed to parse the date time range: " + fromDateTimeStr + " OR " + toDateTimeStr);
            return null;
        }
    }

}
