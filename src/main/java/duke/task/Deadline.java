package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Class representing Deadlines
 */
public class Deadline extends Task {
    protected String by;

    protected LocalDate byDate;

    /**
     * Creates an instance of Deadline
     * @param name Name of the deadline
     * @param by Due date of the deadline
     */
    public Deadline(String name, String by) {
        super(name);
        this.by = by;

        assert !name.equals("") : "name should not be empty";
        assert !by.equals("") : "by date should not be empty";

        // Check if they are in dateTime format
        try {
            byDate = LocalDate.parse(by);
            this.by = byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e) {}
    }

    @Override
    public String fileString() {
        return "D | " + super.fileString() + " | " + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
