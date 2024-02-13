package duke.task;

import java.time.LocalDate;

import duke.exception.DukeException;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructor for Deadline.
     *
     * @param description The description of the deadline task.
     * @param by The deadline of the deadline task.
     */
    public Deadline(String description, LocalDate by, boolean isDone) throws DukeException {
        super(description);
        this.by = by;
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String getDataString() {
        return "D | " + (isDone ? "1" : "0") + " | " + super.getDescription() + " | " + by;
    }
}
