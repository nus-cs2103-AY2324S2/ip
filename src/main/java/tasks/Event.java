package tasks;

/**
 * The `Event` class extends the `Task` class and represents a task with a specific event duration.
 * It includes methods to get the type icon, description with the event duration,
 * and the command format for saving to a file.
 */
public class Event extends Task {

    private String from;
    private String to;

    /**
     * Constructs an `Event` object with the specified description, start time, and end time of the event.
     *
     * @param description The description of the event task.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Gets the type icon for the event task, which is "E".
     *
     * @return The type icon for the event task.
     */
    @Override
    public String getTypeIcon() {
        return "E";
    }

    /**
     * Gets the description of the event task, including the event duration.
     *
     * @return The description of the event task.
     */
    @Override
    public String toString() {
        return String.format("%s (from: %s to: %s)", this.description, this.from, this.to);
    }

    /**
     * Gets the command format for saving the event task to a file.
     *
     * @return The command format for saving the event task to a file.
     */
    public String getCommand() {
        return String.format("event %s /from %s /to %s\n%b\n", this.description, this.from, this.to, this.isDone);
    }
}

