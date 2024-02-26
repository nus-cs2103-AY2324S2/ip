package bob;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

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

    /*
     * A method that checks if two deadlines are equal.
     *
     * @param o The object to compare.
     * @return A boolean representing whether the objects are equal.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        if (!super.equals(o)) {
            return false;
        }

        Deadline deadline = (Deadline) o;

        return Objects.equals(by, deadline.by);
    }
}