import java.util.Objects;

public class Deadline extends Task{
    private final String deadline;

    /**
     * Constructor for Deadline class.
     * @param taskName Name of the task.
     * @param deadline Deadline of the task.
     */
    public Deadline(String taskName, String deadline, boolean isCompleted) {
        super(taskName, isCompleted);
        this.deadline = deadline;
    }

    /**
     * Details regarding the deadline.
     * @return String representation of a deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }

    /**
     * Format of the deadline to be saved in the file.
     * @return String representation of a deadline.
     */
    public String toFileString() {
        return String.format("%s | %d | %s | %s", "D", Objects.equals(super.getStatus(), "X") ? 1 : 0, super.getDesc(), this.deadline);
    }
}
