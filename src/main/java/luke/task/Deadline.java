package luke.task;

import luke.exception.DateException;
import luke.parser.DateTimeParser;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;


/**
 * Represents a deadline task, that has a description and a date and time of the deadline.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructor for Deadline, which takes in the description and date of the deadline.
     *
     * @param description Description of the deadline task.
     * @param by Date and time of the deadline.
     * @throws DateException If the date is not in the correct format.
     */
    public Deadline(String description, String by) throws DateException {
        super(description);
        DateTimeParser dateTimeParser = new DateTimeParser(by);

        this.by = dateTimeParser.getDateTime();

    }

    /**
     * Returns the string representation of the deadline task to be saved in the data file.
     *
     * @return The string representation of the deadline task to be saved in the data file.
     */
    @Override
    public String toDataString() {
        return "D|" + super.toDataString() + "|" + this.by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    /**
     * Returns the string representation of the deadline task to be displayed to the user.
     *
     * @return The string representation of the deadline task to be displayed to the user.
     */
    private String formatDateTime(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatDateTime(this.by) + ")";
    }

}
