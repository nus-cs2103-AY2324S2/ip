package ezra;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {

    protected LocalDateTime by;
    protected String byInput;

    /**
     * Constructs a Deadline object with the specified description and deadline.
     *
     * @param description The description of the task.
     * @param by The deadline of the task in the format "dd/MM/yyyy HHmm".
     * @throws DateTimeParseException If the deadline format is invalid.
     */
    public Deadline(String description, String by) throws DateTimeParseException {
        super(description);
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        this.byInput = by;
    }

    /**
     * Returns a formatted string representation of the Deadline object to display to the user.
     *
     * @return A formatted string including task type, status, description, and deadline.
     */
    @Override
    public String toString() {
        String byString = this.by.format(DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a"));
        return String.format("[D][%s] %s (by: %s)", this.getStatusIcon(), this.description, byString);
    }

    /**
     * Returns a formatted string representation of the Deadline object for storage purposes.
     *
     * @return A formatted string suitable for storage, including task type, status, description, and deadline.
     */
    public String toStorageString() {
        return String.format("D | %d | %s | %s", this.isDone ? 1 : 0, this.description, this.byInput);
    }

    /**
     * Checks if this Deadline object is equal to another object.
     *
     * @param o The object to compare to.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Deadline)) {
            return false;
        }
        Deadline d = (Deadline) o;
        return this.description.equals(d.description) && this.byInput.equals(d.byInput);
    }
}
