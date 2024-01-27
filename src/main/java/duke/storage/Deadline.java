package duke.storage;

/**
 * The Deadline class defines a 'Deadline' task used for the application
 *
 * @author Ryan NgWH
 */
public class Deadline extends Task {
    /**
     * Due date/time of the deadline task
     */
    private String dueDate;

    /**
     * Constructor for a Deadline object
     *
     * @param description Description of the deadline
     * @param dueDate     Due date of the deadline
     */
    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    /**
     * String representation of a Deadline
     *
     * @return String representation of the Deadline
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), dueDate);
    }
}
