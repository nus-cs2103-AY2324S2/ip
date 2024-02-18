package squid.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import squid.constants.CorrectUsage;
import squid.constants.Exceptions;
import squid.constants.Format;
import squid.exceptions.SquidDateException;

/**
 * Class to handle Date/Time formatting.
 */
public class DateTime {
    private String date;
    private LocalDate formattedDate;
    private LocalTime formattedTime;

    /**
     * Initializer to store a string to be understood as a date/time.
     *
     * @param date The string to be parsed.
     * @throws SquidDateException If the string is unable to be parsed.
     */
    public DateTime(String date) throws SquidDateException {
        this.date = date;
        String[] params = this.date.split(",", 2);

        if (params.length > 1) {
            // Take time first.
            formatTime(params[0].toUpperCase().strip());
            formatDate(params[1].strip());
        } else {
            formatDate(date);
        }
    }

    private void formatTime(String time) throws SquidDateException {
        switch (time) {
        case (""):
            break;
        case ("NOW"):
            this.formattedTime = LocalTime.now();
            break;
        default:
            this.formattedTime = parseTime(time);
        }
    }

    private LocalTime parseTime(String time) throws SquidDateException {
        for (int i = 0; i < Format.TIMES.length; i++) {
            try {
                return LocalTime.parse(time, DateTimeFormatter.ofPattern(Format.TIMES[i], Locale.ENGLISH));
            } catch (DateTimeParseException e) {
                try {
                    return LocalTime.parse(time);
                } catch (DateTimeParseException ignored) {
                    ignored = ignored;
                }
            }
        }
        throw new SquidDateException(String.format(Exceptions.SQUID_DATE, time, "time", CorrectUsage.DATE));
    }


    private void formatDate(String date) throws SquidDateException {
        switch (date) {
        case ("today"):
            this.formattedDate = LocalDate.now();
            break;
        case ("tmr"):
            // Fallthrough
        case ("tomorrow"):
            this.formattedDate = LocalDate.now().plusDays(1);
            break;
        default:
            this.formattedDate = parseDate(date);
        }
    }

    private LocalDate parseDate(String date) throws SquidDateException {
        for (int i = 0; i < Format.DATES.length; i++) {
            try {
                return LocalDate.parse(date, DateTimeFormatter.ofPattern(Format.DATES[i]));
            } catch (DateTimeParseException e) {
                try {
                    return LocalDate.parse(date);
                } catch (DateTimeParseException ignored) {
                    ignored = ignored;
                }
            }
        }
        throw new SquidDateException(String.format(Exceptions.SQUID_DATE, date, "date", CorrectUsage.DATE));
    }

    /**
     * Custom formatting for printing DateTime objects.
     *
     * @return The custom format for DateTime objects.
     */
    public String toString() {
        String timeString = "";
        if (this.formattedTime != null) {
            timeString = formattedTime.format(DateTimeFormatter.ofPattern(Format.TIME)) + ", ";
        }
        return timeString + this.formattedDate.format(DateTimeFormatter.ofPattern(Format.DATE));
    }
}
