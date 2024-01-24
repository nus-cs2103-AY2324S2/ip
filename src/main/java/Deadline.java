/**
 * Contains information for the creation of Deadline objects.
 *
 * @author Tee Chu Jie
 */
public class Deadline extends Task {
    /**
     * The deadline of the Deadline object to be created.
     */
    protected String by;

    /**
     * The constructor for a Deadline object.
     *
     * @param description The description of the Deadline object to be created.
     *                    Handled by the super constructor in the Task class.
     * @param by The deadline of the Deadline object.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Converts the Deadline object into a human-readable String to be displayed to the user.
     * @return String object that represents the Deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (" + by + ")";
    }
}
