package checkbot.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

/**
 * The TimeString class is used to parse a string into a LocalDate object.
 * It supports several date formats and also recognizes "yesterday" and
 * "tomorrow" as special cases.
 * The original string is preserved and can be retrieved using the toString
 * method if it cannot be parsed.
 */
public class TimeString {
    private final String dateString;
    private LocalDate date;

    /**
     * Constructs the TimeString object and tries to parse the given date string.
     * If parsing fails, `date` is set to null.
     *
     * @param dateString The given date string to be parsed into.
     */
    public TimeString(String dateString) {
        this.dateString = dateString;
        if (dateString.equalsIgnoreCase("yesterday")) {
            this.date = LocalDate.now().minusDays(1);
            return;
        }
        if (dateString.equalsIgnoreCase("tomorrow")) {
            this.date = LocalDate.now().plusDays(1);
            return;
        }
        final String[] formats = {"dd-MM-yyyy",
                                  "d-M-yyyy",
                                  "d/M/yyyy",
                                  "dd/MM/yyyy",
                                  "d MMM yyyy",
                                  "d MMMM yyyy",
                                  "dd MMM yyyy",
                                  "dd MMMM yyyy",
                                  "MMM d yyyy",
                                  "MMMM d yyyy",
                                  "MMM dd yyyy",
                                  "MMMM dd yyyy"};
        for (String format : formats) {
            try {
                this.date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern(format));
                return;
            } catch (DateTimeParseException ignored) {
                // We don't actually need anything in here because we are trying a bunch of
                // formats
                // to parse the dates in, and if none of them work, we return null.
            }
        }
        this.date = null;
    }

    @Override
    public String toString() {
        return date == null
                ? dateString
                : date.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TimeString)) {
            return false;
        }
        TimeString that = (TimeString) o;
        return Objects.equals(dateString, that.dateString) && Objects.equals(date, that.date);
    }

    /**
     * Compares whether the date in the TimeString is equal to the date passed in as parameter.
     *
     * @param date The date to be compared with.
     * @return True, if both `this.date` and `date` or they are not-null and are equal. False otherwise.
     */
    public boolean timeEquals(LocalDate date) {
        if (this.date == null) {
            return date == null;
        }
        return this.date.equals(date);
    }
}
