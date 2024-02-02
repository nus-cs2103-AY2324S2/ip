package checkbot.task;

/**
 * Represents a deadline task in the task list.
 * A deadline task is a task that contains a "due date".
 */
public class Deadline extends Task {
    private final String to;

    /**
     * Constructor for a Deadline.
     *
     * @param name The name of the Deadline.
     * @param to   When the deadline actually is.
     */
    public Deadline(String name, String to) {
        super(name);
        this.to = tryParseDate(to);
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + this.to + ")";
    }

    @Override
    public String formatForFile() {
        return String.format("D | %s | %s", super.formatForFile(), this.to);
    }
}
