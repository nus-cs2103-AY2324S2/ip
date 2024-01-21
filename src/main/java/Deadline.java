/**
 * Deadlines: tasks that need to be done before a specific date/time.
 *
 * @author Titus Chew
 */
public class Deadline extends Task {

    private final String deadline;

    /**
     * Constructor for a deadline.
     * @param name the name of the deadline
     * @param deadline the deadline (date/time) of the task
     */
    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[T]%s (by: %s)", super.toString(), deadline);
    }
}
