package tommy.task;

/**
 * Represents the task with a deadline in the format [ <code>details</code> (by: <code>date</code>) ].
 */
public class Deadline extends Task {
    private static final String symbol = "[D]";

    /**
     * Constructor for a Deadline task with its status as default not done.
     *
     * @param description Description of the deadline task.
     */
    public Deadline(String description) {
        super(description);
    }

    /**
     * Constructor for a Deadline task with its status dependent on the input.
     * Used when retrieving tasks from the database.
     *
     * @param description Description of the deadline task.
     * @param isDone Status of the deadline task.
     */
    public Deadline(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return symbol + this.getStatusIcon() + this.description;
    }

}
