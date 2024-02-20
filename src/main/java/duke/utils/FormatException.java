package duke.utils;

/**
 * Class to raise exceptions for any input format errors.
 *
 * @author KohGuanZeh
 */
public class FormatException extends Exception {
    public FormatException(String errorMessage) {
        super(errorMessage);
    }
}
