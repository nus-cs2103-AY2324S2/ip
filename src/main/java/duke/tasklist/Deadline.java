package duke.tasklist;

import duke.exception.MalformedUserInputException;
import duke.common.DateTimeHandler;

/**
 * Represents a Deadline.
 */
public class Deadline extends Task {

    private DateTimeHandler by;

    /**
     * Creates a Deadline task with the given description, deadline, and status.
     *
     * @param description the name of the deadline task.
     * @param by the deadline of the task in the format "yyyy-MM-dd".
     * @param isDone the status of the task (true if completed, false otherwise).
     * @throws MalformedUserInputException if the user input is dirty.
     */
    public Deadline(String description, String by, boolean isDone) throws MalformedUserInputException {
        super(description, isDone);
        this.by = new DateTimeHandler(by);
    }

    /**
     * Returns a string representation of the Deadline object.
     * @return a string representation of the Deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Returns a string representation of the Task object in a format suitable for storage.
     * @return a string representation of the Task object in a format suitable for storage
     */
    @Override
    public String toStorageString() {
        return "D | " + this.getDescription() + " | " + super.getStatus() + " | " + this.by.toStorageString();
    }


}
