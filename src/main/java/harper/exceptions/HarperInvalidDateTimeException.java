package harper.exceptions;

/**
 * Exception that indicates the date time format is invalid.
 */
public class HarperInvalidDateTimeException extends HarperException {
    //CHECKSTYLE.OFF: MissingJavadocMethod
    public HarperInvalidDateTimeException() {
        super("Date time should follow this format:\n"
                + "d/M/yyyy HH:mm (e.g., 15/9/2024 16:25)");
    }
}
