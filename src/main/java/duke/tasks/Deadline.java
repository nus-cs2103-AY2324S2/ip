package duke.tasks;

import duke.exceptions.InvalidDateTimeException;
import duke.utils.DukeDateFormater;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
	private LocalDate by;
	private DukeDateFormater formater = new DukeDateFormater();

	public Deadline(Boolean status, String detail, String by) throws DateTimeParseException {
		super(status, detail);
		try {
			this.by = this.formater.stringToDate(by);
		} catch (DateTimeParseException e) {
			throw new InvalidDateTimeException();
		}
	}

	@Override
	public String inFileStringFormat() {
		return "D|" + super.inFileStringFormat() + "|" + this.by.toString();
	}

	@Override
	public String toString() {
		return "[D]" + super.toString() + "(by: " + this.formater.dateToString(this.by) + ")";
	}
}
