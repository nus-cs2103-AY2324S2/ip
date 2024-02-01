package exceptions;

import java.time.format.DateTimeParseException;

public class IllegalDateFormatException extends DateTimeParseException {

    public IllegalDateFormatException(String mssg, String parsedData) {
        super(mssg, parsedData, 0);
    }
}
