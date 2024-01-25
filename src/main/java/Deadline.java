/**
 * Represents a deadline, which is a specialized form of Task.
 * This class adds a specific deadline to the task description.
 */
public class Deadline extends Task {
    private static final String DEADLINE_PREFIX = "[D]";
    private final String deadline;

    /**
     * Constructs a new Deadline with the given description and deadline.
     *
     * @param description The description of the deadline.
     * @param deadline The deadline date as a String.
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Returns a string representation of the Deadline.
     * The string includes the Deadline identifier, followed by the Task's string representation,
     * and the specified deadline.
     */
    @Override
    public String toString() {
        return DEADLINE_PREFIX + super.toString() + "(by: " + this.deadline + ")";
    }
}
