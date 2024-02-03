package task;

/**
 * Represents a Deadline task.
 * A <code>Deadline</code> object corresponds to a task with a description and a
 * deadline.
 * e.g., <code>Deadline read book /by 2020-02-02</code>
 */
public class Deadline extends Task {
    public static final String TYPE = "D";
    private final java.time.LocalDate deadline;

    /**
     * Constructs a Deadline object with the specified description and deadline.
     *
     * @param description The description of the deadline.
     * @param deadline    The deadline of the deadline.
     */
    public Deadline(int taskID, String description, java.time.LocalDate deadline) {
        super(taskID, description);
        this.deadline = deadline;
    }

    /**
     * Constructs a Deadline object with the specified description, deadline, and
     * status.
     *
     * @param description The description of the deadline.
     * @param deadline    The deadline of the deadline.
     * @param isDone      The status of the deadline.
     */
    public Deadline(int taskID, String description, java.time.LocalDate deadline, boolean isDone) {
        super(taskID, description, isDone);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)", TYPE, super.toString(), this.deadline);
    }
}
