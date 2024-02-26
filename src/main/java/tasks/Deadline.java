package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import exceptions.ArgumentException;
import parser.Parser;


/**
 * Represents a Task with an ending date.
 */
public class Deadline extends Task {

    protected LocalDate dueBy;

    /**
     * Instantiates Deadline object with description and ending date. Defaults status to not done.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.dueBy = by;
    }

    /**
     * Instantiates Deadline object with description, ending date and status.
     */
    public Deadline(String description, LocalDate by, String status) {
        super(description, status);
        this.dueBy = by;
    }

    /**
     * {@inheritDoc}
     * Prepends the task type and appends the ending date of this object.
     */
    @Override
    public String toSaveFormat() {
        return "D " + super.toSaveFormat() + " /by " + dueBy;
    }

    @Override
    public void update(String details) throws ArgumentException {
        String[] args = Parser.parseDeadlineArgument(details);
        this.description = args[0].trim();
        this.dueBy = LocalDate.parse(args[1].trim());
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueBy.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
