package duke.task;

/**
 * Represents a deadline task.
 * A <code>Deadline</code> object corresponds to a deadline task represented by a description, a status and a deadline
 */
public class Deadline extends Task {
    protected String deadline;

    /**
     * Creates an instance of a Deadline task.
     * @param description The description of the deadline task.
     * @param deadline The date in the format MMM d yyyy.
     */
    public Deadline(String description, int isDone, String deadline) {
        super(description, isDone);
        this.deadline = deadline;
    }

    /**
     * Returns the string output of the Deadline task
     * @return the string output of the Deadline task
     */
    public String toString() {
        return "[D]" + "[" + getStatusIcon() + "] " + this.description + " (by: " + this.deadline + ")";
    }
}
