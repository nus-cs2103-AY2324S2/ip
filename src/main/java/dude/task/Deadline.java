package dude.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline task.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Creates deadline object.
     * @param description Deadline description.
     * @param by Deadline due date.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }


    /**
     * Creates deadline object.
     * @param description Deadline description.
     * @param done Whether task is done.
     * @param by Deadline due date.
     */
    public Deadline(String description, boolean done, String by) {
        super(description, done);
        this.by = LocalDate.parse(by);
    }

    @Override
    public String getStorageString() {
        return String.format("D | %s | %s", super.getStorageString(), this.by);
    }

    @Override
    public boolean isOnDate(LocalDate date) {
        return date.isEqual(this.by);
    };

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
