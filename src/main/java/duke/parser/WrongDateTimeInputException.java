package duke.parser;

/**
 * Represents exceptions that arise when input date&time format is not as expected
 */
public class WrongDateTimeInputException extends InputException {
    /**
     * Constructor of WrongDateTimeInputException
     */
    public WrongDateTimeInputException() {
        super("Wrong date&time input format " + '\n'
                + "Please key in date&time as " + Parser.DATETIME_FORMAT);
    }
}