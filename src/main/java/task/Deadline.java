package task;

/**
 * Represents a "Deadline" task,
 * which is a task with a deadline time attached.
 *
 * @author Maximilliano Utomo
 */
public class Deadline extends Task {
    /**
     * The due date of the task.
     */
    protected String deadline;

    /**
     * A public constructor for the task.Deadline.
     * @param desc - the description of the task
     * @param deadline - the deadline of the task
     */
    public Deadline(String desc, String deadline) {
        super(desc);
        this.deadline = deadline;
    }

    @Override
    public String toDataFormat() {
        return "D|" + super.toDataFormat() + "|" + this.deadline;
    }

    /**
     * Represent the task into a String format applicable for printing output.
     * Uses an extra [D] to represent a task.Deadline.
     * @return A String representation of the task.Deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }
}
