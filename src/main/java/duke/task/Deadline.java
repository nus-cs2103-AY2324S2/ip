package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

import duke.exception.DukeException;

/**
 * The `Deadline` class represents a task with a specific deadline in Duke.
 */
public class Deadline extends Task {

    /**
     * The formatted string representation of the deadline.
     */
    protected String by;

    /**
     * The input date-time formatter.
     */
    protected DateTimeFormatter input = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * The output date-time formatter for display.
     */
    protected DateTimeFormatter output = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    /**
     * Constructs a `Deadline` with the given description and deadline.
     *
     * @param description The description of the deadline.
     * @param by The deadline in yyyy-MM-dd HH:mm format.
     * @throws DukeException If there is an issue parsing the deadline or the format is invalid.
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);

        try {
            LocalDateTime date = LocalDateTime.parse(by, input);
            this.by = date.format(output);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid time format. Please use yyyy-MM-dd HH:mm.");
        }
    }

    /**
     * Returns a string representation of the deadline task, including its status icon and the deadline.
     *
     * @return String representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Returns a formatted string for writing the deadline task to a file.
     *
     * @return Formatted string for file representation.
     */
    @Override
    public String toFileString() {
        return "D" + super.toFileString() + " | " + LocalDateTime.parse(by, output).format(input);
    }

    /**
     * Indicates whether some other object is "equal to" this one. Equality is based on task properties.
     *
     * @param obj The reference object with which to compare.
     * @return True if this object is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Deadline deadline = (Deadline) obj;
        return this.isDone == deadline.isDone
                && this.description.equals(deadline.description)
                && this.by.equals(deadline.by);
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.description, this.isDone, this.by);
    }
}
