/**
 * Deadlines are tasks that need to be done before a specific date/time.
 */
public class Deadline extends Task {
    protected String deadline = "";

    /**
     * Constructor for a Deadline object.
     *
     * @param name task name
     * @param d deadline
     */
    public Deadline(String name, String d) {
        super(name);
        this.deadline = d;
    }

    /**
     * toString method for printing task description.
     * @return task type + task status + task name
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}
