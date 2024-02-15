package duke.task;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;


/**
 * Consists of information related to Deadline.
 */
public class Deadline extends Task {

    private LocalDateTime by;

    /**
     * Constructs a Deadline Object.
     * @param description Contains the type of deadline
     * @param by Contains the date and time so as to when should the deadline be completed.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * String representation of the deadline.
     * @return formatted string with the deadline task.
     */


    @Override public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[D]" + (this.checkDone() ? "[X] " : "[ ] ") + this.getDescription() + " (by: " + this.by.format(formatter) + ")";

    }

    public LocalDate getRawDate() {
        return this.by.toLocalDate();
    }

    /**
     * Returns the formatted deadline date and time as a string.
     * @return Date and Time in a particular format.
     */
    public LocalDateTime getBy() {
        return by;
    }
}
