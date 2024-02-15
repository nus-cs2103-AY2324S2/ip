import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
 * This class represents a deadline that can be added to the tasklist.
 */
class Deadline extends Task {
    protected LocalDateTime by;

    /*
     * A constructor for creating a new task with a deadline.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /*
     * A method to get by.
     *
     * @return The deadline due.
     */
    public LocalDateTime getBy() {
        return this.by;
    }

    /*
     * A method that returns the task status as a string.
     *
     * @return A label [D] and a check-box followed by the description of the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }
}