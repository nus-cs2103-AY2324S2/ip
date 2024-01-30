/**
 * Deadlines are tasks that need to be done before a specific date/time.
 */
public class Deadline extends Task {
    /**
     * Constructor for a Deadline object.
     *
     * @param name task name
     * @param d deadline
     */
    public Deadline(String name, String d) {
        super(name + " (by: " + d + ")");
    }

    /**
     * Constructor for loading from file.
     *
     * @param description of task
     * @param b isDone
     */
    public Deadline(String description, boolean b) {
        super(description, b);
    }

    /**
     * toString method for printing task description.
     * @return task type + task status + task name
     */
    @Override
    public String toString() {
        return "[D]" + super.toString();
    }
}
