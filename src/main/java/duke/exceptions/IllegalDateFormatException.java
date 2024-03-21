package duke.exceptions;

import java.time.format.DateTimeParseException;

/**
 * A custom exception that gives error message due to inavlid date and time format.
 */
public class IllegalDateFormatException extends DateTimeParseException {

    public IllegalDateFormatException(String mssg, String parsedData) {
        super(mssg, parsedData, 0);
    }
}
