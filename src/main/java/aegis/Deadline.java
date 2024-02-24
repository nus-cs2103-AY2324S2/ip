package aegis;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class represents a deadline type of task. A deadline object contains
 * a LocalDate field that stores the complete by date of the task.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructor for creating a Deadline object.
     *
     * @param description Description of the deadline task.
     * @param by Complete by date of the deadline task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    /**
     * Returns the deadline task as a string containing its task type, completion status,
     * description and complete by date.
     *
     * @return Deadline task as a string.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.generateByDateString() + ")";
    }

    /**
     * Returns a string in a format for saving to file.
     * String is formatted for easy parsing when deadline task is reconstructed using contents
     * of the string.
     *
     * @return String formatted for saving to file.
     */
    @Override
    public String toTaskSaveString() {
        return "D|" + this.getStatusInt() + "|" + this.description + "|" + this.generateBySaveString();
    }

    /**
     * Returns the complete by date of the deadline task in a format for better readability.
     * Format follows MMM dd yyyy e.g. Jan 02 2024.
     *
     * @return Complete by date of deadline task in format for better readability.
     */
    private String generateByDateString() {
        return this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    /**
     * Returns the complete by date of the deadline task in a format for saving to file.
     * Format follows yyyy-MM-dd e.g. 2024-01-02.
     *
     * @return Complete by date of deadline task in format for saving to file.
     */
    private String generateBySaveString() {
        return this.by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
