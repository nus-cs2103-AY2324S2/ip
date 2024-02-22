//// Solution below adapted by week2 iP Level-3 Partial solution
package ping.job;
import java.time.LocalDate;

/**
 * This class is used to create a deadline task
 */
public class Deadline extends Task {
    protected LocalDate by;
    protected String description;

    /**
     * This is the constructor of the Deadline class
     * @param description the description of the deadline task
     * @param by the date of the deadline task
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    
    public LocalDate getBy() {
        return by;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.by + ")";
    }
}
