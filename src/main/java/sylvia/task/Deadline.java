package sylvia.task;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    private TaskDateTime dueTime;

    /**
     * Creates a new deadline with the given description and due time.
     *
     * @param description The description of the deadline.
     * @param due         The due time of the deadline.
     * @throws SylviaDateTimeParseException If the due time is not in the correct
     *                                      format.
     */
    public Deadline(String description, String due) throws SylviaDateTimeParseException {
        super(description);
        this.dueTime = new TaskDateTime(due);
    }

    /**
     * Creates a new deadline with the given description, due time and done status.
     *
     * @param description The description of the deadline.
     * @param due         The due time of the deadline.
     * @param isDone      The done status of the deadline.
     * @throws SylviaDateTimeParseException If the due time is not in the correct
     *                                      format.
     */
    public Deadline(String description, String due, boolean isDone) throws SylviaDateTimeParseException {
        super(description, isDone);
        this.dueTime = new TaskDateTime(due);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (due: " + dueTime + ")";
    }

    /**
     * Returns the serialized string of this task. The serialized string will be
     * stored in the data file.
     *
     * @return The serialized string of the deadline.
     */
    @Override
    public String serialize() {
        // assuming that the dueTime does not contain "|"
        return "D | " + super.serialize() + " | " + dueTime.serialize();
    }
}
