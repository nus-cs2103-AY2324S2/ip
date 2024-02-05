package tasklist.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Deadline Task that needs to be completed. It is an extension of the Task class.
 * Contains 'by' date to specify the deadline of the task.
 */
public class Deadline extends Task {
    /** deadline of the task in LocalDateTime format */
    protected LocalDateTime deadlineDate;

    /** deadline of the task in String format */
    protected String byDateString;

    /**
     * Initalize a Deadline.
     *
     * @param item the task to be completed.
     * @param byDate the deadline of the task.
     */
    public Deadline(String item, String byDate) {
        super(item);
        try {
            String byDateString = byDate.substring(byDate.indexOf("/to") + 3, byDate.length());
            if (byDateString.trim() == "") {
                throw new EmptyDateException();
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy HHmm");
            LocalDateTime deadlineDate = LocalDateTime.parse(byDateString.trim(), formatter);
            this.deadlineDate = deadlineDate;
            this.byDateString = byDateString.trim();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException("Please enter a date");
        } catch (DateTimeParseException dte) {
            throw new DateTimeParseException(
                    "Date format is incorrect, please try again", byDate, 0, dte);
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byDateString + ")";
    }
}
