package seedu.duke.task;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task . A <code>Deadline</code> object corresponds to a deadline task created by the user
 * it contains additional datetime information about the deadline of the task. E.g. 2022-10-10 18:00
 */
public class Deadline extends Task {
    private LocalDateTime deadline;
    private String deadlineString;

    /**
     * Constructor of the Deadline object
     *
     * @param description The description of the deadline
     * @param hasDone     Whether the deadline is done
     * @param deadline    The datetime of the deadline
     */
    public Deadline(String description, boolean hasDone, LocalDateTime deadline) {
        this.deadline = deadline;
        this.deadlineString = deadline.format(DateTimeFormatter.ofPattern("MM dd yyyy HH:mm"));
        super.setDescription(description);
        super.setHasDone(hasDone);
    }

    /**
     * Returns the datetime of deadline
     *
     * @return the datetime of deadline
     */
    public LocalDateTime getDeadline() {
        return deadline;
    }


    /**
     * Returns a string representation of the datetime of deadline
     *
     * @return the string representation of the datetime of deadline
     */
    public String getDeadlineString() {
        return deadlineString;
    }

    /**
     * Returns a string representation of the deadline
     *
     * @return the string representation of the deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadlineString + ")";
    }
}
