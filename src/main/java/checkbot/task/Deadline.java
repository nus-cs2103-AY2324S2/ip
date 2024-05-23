package checkbot.task;

import java.util.Objects;

/**
 * Represents a deadline task in the task list.
 * A deadline task is a task that contains a "due date".
 */
public class Deadline extends Task {
    private final TimeString to;

    /**
     * Constructor for a Deadline.
     *
     * @param name The name of the Deadline.
     * @param to   When the deadline actually is.
     */
    public Deadline(String name, String to) {
        super(name);
        this.to = new TimeString(to);
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + this.to + ")";
    }

    @Override
    public String formatForFile() {
        return String.format("D | %s | %s", super.formatForFile(), this.to);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Deadline) || !super.equals(o)) {
            return false;
        }
        Deadline deadline = (Deadline) o;
        return Objects.equals(to, deadline.to);
    }
}
