package yapchit.yapchitbackend.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class representing a Deadline object.
 *
 * Extends Task class
 */
public class Deadline extends Task {

    private LocalDate by;

    /**
     * Constructor of new Deadline object
     *
     * @param name name of Deadline
     */
    public Deadline(String name, LocalDate by) {
        super(name);
        this.by = by;
    }

    /**
     * Prints the Deadline details onto the screen.
     *
     * @return
     */
    @Override
    public String toString() {
        String tag = super.getDone() ? "[X]" : "[ ]";
        String ret =  "[D]"
                + tag
                + " " + super.getName().strip()
                + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ")";

        return super.wrapToString(ret);
    }

    /**
     * Getter method which returns the 'by' date of the deadline.
     *
     * @return LocalDate representing the due date of the deadline.
     */
    public LocalDate getBy() {
        return this.by;
    }

    public void setBy(LocalDate by) {
        this.by = by;
    }
}
