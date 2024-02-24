package johnny.tasks;

import java.time.LocalDateTime;

/**
 * Represents a deadline.
 */
public class Deadline extends Task {

    /** When the Deadline is due by. */
    private LocalDateTime by;

    /**
     * Constructor for Deadline.
     * Calls super class constructor to store name of task.
     *
     * @param name Name of the Deadline.
     * @param by When the Deadline is due by.
     */
    public Deadline(String name, LocalDateTime by) {
        super(name);
        this.by = by;
    }

    /**
     * Formats Deadline for Ui to print.
     *
     * @return String representation of Deadline for printing.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + super.formatOutputDate(by) + ")";
    }

    /**
     * Formats Deadline for storing in Storage.
     *
     * @return String representation of Deadline to store.
     */
    @Override
    public String addToFile() {
        return "D | " + super.addToFile() + " | " + super.formatInputDate(by) + "\n";
    }

}
