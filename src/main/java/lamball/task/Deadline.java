package lamball.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import lamball.exception.InvalidDateException;


/**
 * Deadline class, an extension of the Task class that contains a due date.
 *
 * @author ongzhili
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructor for Deadline task.
     *
     * @param description Name of task.
     * @param by due date of deadline
     * @throws DateTimeParseException if invalid date is provided.
     */
    public Deadline(String description, String by) throws DateTimeParseException {
        super(description);

        LocalDate dateToAdd = LocalDate.parse(by);

        if (dateToAdd.isBefore(LocalDate.now())) {
            throw new InvalidDateException("Parsed date is in the paaast.");
        }
        this.by = LocalDate.parse(by);
    }

    @Override
    public String command() {
        return "deadline " + description + " /by " + by;
    }

    @Override
    public String toString() {
        String formattedDate = by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D]" + super.toString() + " (by: " + formattedDate + ")";
    }
}
