package irwyn.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class encapsulates the Deadline class.
 * It represents a task deadline.
 *
 * @author Irwyn Liong
 * @version Week-3
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Constructor for a Deadline object.
     *
     * @param description The description of the deadline.
     * @param by The end date and time of the deadline.
     */
    public Deadline(String description, String by) {
        super(description, TaskType.DEADLINE);
        this.by = stringToDateTime(by);
    }

    /**
     * This method converts a string into a LocalDateTime object.
     * If the string is in the format "yyyy-MM-dd", it will be treated as a date at the start of the day.
     * If the string is in the format "yyyy-MM-ddTHH:mm:ss", it will be treated as a date with time.
     * Otherwise, the string is expected to be in the format "yyyy-MM-dd HH:mm:ss".
     *
     * @param dateTime The string to be converted into a LocalDateTime object.
     * @return A LocalDateTime object that represents the date and time specified by the input string.
     */
    private LocalDateTime stringToDateTime(String dateTime) {
        if (dateTime.length() <= 10) {
            return LocalDateTime.parse(dateTime + "T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        } else if (dateTime.contains("T")) {
            return LocalDateTime.parse(dateTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        } else {
            return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }
    }


    @Override
    public String toFileFormat() {
        return "D | " + super.toFileFormat() + " | " + by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }
}
