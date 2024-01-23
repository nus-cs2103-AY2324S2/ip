package task;

/**
 * Deadlines: tasks that need to be done before a specific date/time.
 *
 * @author Titus Chew
 */
public class Deadline extends Task {

    private final String deadline;

    /**
     * Constructor for a deadline.
     * @param name The name of the deadline.
     * @param deadline The deadline (date/time) of the task.
     */
    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    /**
     * Gets a human-readable description of the task.
     * @return The task in a human-readable string.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), deadline);
    }
}
