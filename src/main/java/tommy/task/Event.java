package tommy.task;

/**
 * Represents an Event task with the format [ details (from: <code>date</code>) to: <code>date</code>) ].
 */
public class Event extends Task {
    private static final String symbol = "[E]";

    /**
     * Constructor for an Event task with status as default not done.
     *
     * @param description Description of the Event task.
     */
    public Event(String description) {
        super(description);
    }

    /**
     * Constructor for an Event task with its status dependent on the input.
     * Used when retrieving tasks from the database.
     *
     * @param description Description of the Event task.
     * @param isDone Status of the Event task.
     */
    public Event(String description, Boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return symbol + this.getStatusIcon() + this.description;
    }
}

