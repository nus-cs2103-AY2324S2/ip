package harper.exceptions;

/**
 * Exception that indicates the format of command to add deadline is incorrect.
 */
public class HarperInvalidDeadlineException extends HarperException {
    //CHECKSTYLE.OFF: MissingJavadocMethod
    public HarperInvalidDeadlineException() {
        super("Please follow the format: \"deadline [description] /by [deadline]\"\n"
                + "to add a deadline task into your list.\n"
                + "All fields are required.\n"
                + "Only the whitespace after \"deadline\" is required.");
    }
}
