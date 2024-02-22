package seedu.banter.errors;

import seedu.banter.ui.Ui;


/**
 * Represents an error that occurs when the user uses an invalid date time format.
 */
public class InvalidBanterDateTimeError extends BanterError {
    /**
     * Constructs a new InvalidBanterDateTimeError object.
     */
    public InvalidBanterDateTimeError() {
        super(Errors.INVALID_DATE_TIME_FORMAT + "\n" + Ui.DATE_TIME_FORMAT);
    }
}
