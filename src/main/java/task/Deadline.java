package task;

/**
 * Deadlines: tasks that need to be done before a specific date/time.
 *
 * @author Titus Chew
 */
public class Deadline extends Task {

    private final String deadline;

    /**
     * Constructor for this deadline.
     *
     * @param name the name of this deadline
     * @param deadline the deadline (date/time) of this task
     */
    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    /**
     * Gets a human-readable description of this task.
     *
     * @return this task in a human-readable string
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), deadline);
    }
}
