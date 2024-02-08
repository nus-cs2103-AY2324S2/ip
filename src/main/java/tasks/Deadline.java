package tasks;

/**
 * <p>
 *  represents a Deadline Task that a user would want to save, it has
 *  a description and a dueDate.
 *  </p>
 */
public class Deadline extends Task {
    private final String date;
    /**
     * initializes a Deadline clas
     * @param description describes a Deadline
     * @param date due date of the Deadline
     */
    public Deadline(String description, String date) {
        super(description, 'D');
        this.date = date;
    }

    /**
     * initializes a Deadline clas
     * @param description describes a Deadline
     * @param isDone indicates if a Deadline has been marked as done
     * @param date due date of the Deadline
     */
    public Deadline(String description, Boolean isDone, String date) {
        super(description, isDone, 'D');
        this.date = date;
    }
    @Override
    public String toString() {
        return super.toString() + String.format(" (by: %s)\n", this.date);
    }

    @Override
    public String prepareForStorage() {
        return super.prepareForStorage() + String.format(" | %s", this.date);
    }
}
