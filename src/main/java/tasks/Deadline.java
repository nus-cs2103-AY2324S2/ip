package tasks;

/**
 * <p>
 *  represents a Deadline Task that a user would want to save, it has
 *  a description and a dueDate.
 *  </p>
 */
public class Deadline extends Task {
    private final String date;
    public Deadline(String description, String date) {
        super(description, 'D');
        this.date = date;
    }

    public Deadline(String description, Boolean isDone, String date) {
        super(description, isDone,'D');
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