package checkbot.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public abstract class Task {
    private final String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    /**
     * Attempts to parse the specified string as a LocalDate object.
     * Reformats the date and returns the new string, if successful,
     * or returns the original string otherwise.
     *
     * @param dateString The string to be parsed into a LocalDate
     * @return Formatted date, or the original string if parsing fails.
     */
    public static String tryParseDate(String dateString) {
        final String[] FORMATS = {
                "dd-MM-yyyy",
                "d-M-yyyy",
                "d/M/yyyy",
                "dd/MM/yyyy"
        };
        for (String format : FORMATS) {
            try {
                LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern(format));
                return date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
            } catch (DateTimeParseException ignored) {
            }
        }
        return dateString;
    }

    public String formatForFile() {
        return String.format("%d | %s", this.isDone ? 1 : 0, this.name);
    }

    @Override
    public String toString() {
        return "[" + (this.isDone ? "X" : " ") + "] " + this.name;
    }

    public boolean nameContains(String substr) {
        return this.name.toLowerCase().contains(substr.toLowerCase());
    }
}
