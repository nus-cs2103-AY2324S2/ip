package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class represents a deadline
 */
public class Deadline extends Task {
    protected LocalDateTime by; // date/time to be done before

    public Deadline(String description, boolean completed, LocalDateTime by) {
        super(description, completed);
        this.by = by;
    }

    public LocalDateTime getBy() {
        return this.by;
    }

    /**
     * Gets the deadline in LocalDateTime and changes it to String format.
     *
     * @return The deadline in String format.
     */
    public String getByString() {
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return this.by.format(dateTimeFormat);
    }

    public void setBy(LocalDateTime dateTime) {
        this.by = dateTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getByString() + ")";
    }
}
