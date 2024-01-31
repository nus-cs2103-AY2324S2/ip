package duke.tasks;

import duke.exceptions.InvalidDateTimeException;
import duke.utils.DukeDateFormater;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Class represent Task type Deadline.
 */
public class Deadline extends Task {
	private LocalDate by;
	private DukeDateFormater formater = new DukeDateFormater();

    /**
     * Initializes a Deadline object with given params.
     * @param status True for completed, False for not completed yet.
     * @param detail Detail information of the task.
     * @param by Task end time.
     * @throws DateTimeParseException If the end time is invalid.
     */
    public Deadline(Boolean status, String detail, String by) throws DateTimeParseException {
        super(status, detail);
        try {
            this.by = this.formater.stringToDate(by);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException();
        }
    }

    /**
     * Formats object to be stored in file.
     * @return Formatted string to be stored in file.
     */
    @Override
    public String inFileStringFormat() {
        return "D|" + super.inFileStringFormat() + "|" + this.by.toString();
    }

	@Override
	public String toString() {
		return "[D]" + super.toString() + "(by: " + this.formater.dateToString(this.by) + ")";
	}
}
