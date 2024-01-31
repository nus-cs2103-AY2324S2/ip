/**
 * This class represents a Task with a deadline.
 */
public class Deadline extends Task {

    private static final String TYPE_SYMBOL = "[D]";
    private final String deadline;

    /**
     * Constructs a new deadline Task with the specified description, and deadline.
     *
     * @param description Description of the deadline Task
     * @param deadline Deadline of the Task
     * @throws MeanDukeException if deadline is invalid
     */
    public Deadline(String description, String deadline) throws MeanDukeException {
        super(description, TYPE_SYMBOL);
        if (deadline.isEmpty()) {
            throw new MeanDukeException("Usage: \"add deadline <description> /by <deadline>\"");
        }
        this.deadline = deadline;
    }

    /**
     * Constructs a new deadline Task with the specified description, completion status, and deadline.
     *
     * @param description Description of the deadline Task
     * @param isDone boolean value that determines if the initialised deadline Task is completed or not
     * @param deadline Deadline of the Task
     * @throws MeanDukeException if deadline is invalid
     */
    public Deadline(String description, boolean isDone, String deadline) {
        super(description, TYPE_SYMBOL, isDone);
        this.deadline = deadline;
    }

    public String saveString() {
        return "DEADLINE" + "\n" + super.saveString() + "\n" + this.deadline;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + this.deadline + ")";
    }
}
