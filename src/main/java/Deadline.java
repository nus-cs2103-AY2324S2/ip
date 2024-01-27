/**
 * Deadline class for tasks with specified deadline.
 */
public class Deadline extends Task{
    /** Deadline of task recorded */
    private String deadline;

    public Deadline(String task, String deadline) {
        super(task);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }

    @Override
    public String getData() {
        return "D" + super.getData() + " | " + this.deadline;
    }
}
