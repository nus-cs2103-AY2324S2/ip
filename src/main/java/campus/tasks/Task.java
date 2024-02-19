package campus.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Class object which represents a Task, which has a name and a status completion.
 */
abstract public class Task {
    public boolean isCompleted;
    public String taskName;
    public DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm dd/MM/yyyy");

    /**
     * Checks to see if the input string is of the correct format pattern.
     * @param input timedate string.
     * @param formatter HHmm dd/MM/yyyy.
     * @return true if yes, false if no.
     */
    public static boolean isValidDateTimeFormat(String input, DateTimeFormatter formatter) {
        try {
            LocalDateTime.parse(input, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    abstract public void markComplete();

    abstract public void markIncomplete();

    abstract public String toDBFormat();
}
