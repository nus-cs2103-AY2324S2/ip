package remi.model;

/**
 * Represents a deadline task that ends by a certain time. Subclass of Task.
 */
public class Deadline extends Task {
    private String by;

    /**
     * Simple constructor for the deadline. Formats the dates according to the formatDate() method.
     * Works with dates only, not time.
     *
     * @param label name of the task
     * @param by ending time
     */
    public Deadline(String label, String by) {
        super(label);
        this.by = formatDate(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Returns a parsable string of the deadline task. Meant to be used for remi.storage purposes.
     *
     * @return a parsable string representation of the task and all its details
     */
    public String getParsableString() {
        return "D|" + super.getParsableString() + "|" + by;
    }
}
