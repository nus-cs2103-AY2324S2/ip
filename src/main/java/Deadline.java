/**
 * Encapsulates DEADLINE task. Inherits from Task.
 *
 * @author Tan Qin Yong
 */
public class Deadline extends Task {

    /**
     * The deadline associated with the task.
     */
    private String by;

    /**
     * Constructor for deadline object.
     *
     * @param description The description of the deadline task.
     * @param by          The deadline associated with the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the Deadline object.
     *
     * @return A string representation of the Deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
