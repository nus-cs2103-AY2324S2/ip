package virtue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/** Represents a date and time for tasks in the chatbot. */
public class VirtueDateTime {
    private LocalDateTime dateTime;

    /**
     * Creates a date/time object from the string input.
     * @param str The date/time in ISO_LOCAL_DATE_TIME format.
     * @throws DateTimeParseException If not a valid date/time or not in the correct format.
     */
    public VirtueDateTime(String str) throws DateTimeParseException {
        dateTime = LocalDateTime.parse(str);
    }

    /**
     * Generates the string representation when the task
     * containing this object is printed to the user.
     * @return The string representation when the task is printed to the user.
     */
    @Override
    public String toString() {
        return dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
    }

    /**
     * Generates the string representation when the
     * task containing this object is saved to the file.
     * @return The string representation when the task is saved to the file.
     */
    public String fileFormat() {
        return dateTime.toString();
    }
}
