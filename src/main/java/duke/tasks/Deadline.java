package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.utils.Parser;


/**
 * Class represent Task type Deadline.
 */
public class Deadline extends Task {
    private LocalDate by;

    /**
     * Initializes a Deadline object with given params.
     * @param status True for completed, False for not completed yet.
     * @param detail Detail information of the task.
     * @param by Task end time.
     * @throws DateTimeParseException If the end time is invalid.
     */
    public Deadline(Boolean status, String detail, LocalDate by) throws DateTimeParseException {
        super(status, detail);
        this.by = by;
    }

    /**
     * Formats object to be stored in file.
     * @return Formatted string to be stored in file.
     */
    @Override
    public String inFileStringFormat() {
        return "D|" + super.inFileStringFormat() + "|" + by.toString();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + Parser.FORMATER.dateToString(this.by) + ")";
    }

    public LocalDate getBy() {
        return by;
    }
}
