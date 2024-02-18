package nihao.exception;

/**
 * Represents an Exception thrown when the given String has a wrong format.
 */
public class IncorrectDateTimeFormatException extends Exception {
    /**
     * Class constructor.
     */
    public IncorrectDateTimeFormatException() {
        super("IncorrectDateTimeFormatException: "
                + "only accepts 'dd/MM/yyyy HHmm' format");
    }
}
