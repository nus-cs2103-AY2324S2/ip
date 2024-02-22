package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * For the creation of a Deadline class
 */
public class Deadline extends Task {

    protected LocalDate dateDue;

    /**
     * Constructor class for a Deadline class
     * @param name Name of the task
     * @param dateDue Date when the deadline is due
     */
    public Deadline(String name, LocalDate dateDue) {
        super(name);
        this.dateDue = dateDue;
    }
    @Override
    protected String taskTypeDisplay() {
        return "[D]";
    }
    @Override
    public String toString() {
        return String.format("%s%s %s (by: %s)", this.taskTypeDisplay(), this.completionDisplay(), this.name,
                this.dateDue.format(DateTimeFormatter.ofPattern("dd MMM yyyy")));
    }
    @Override
    public String storeFormat() {
        String completeFormat = isComplete ? "1" : "0";
        return String.format("%s | %s | %s | %s", "D",
                completeFormat, name, dateDue.format(DateTimeFormatter.ISO_DATE));
    }
}
