package harper.exceptions;

/**
 * Exception that indicates the format of command to add event is incorrect
 */
public class HarperInvalidEventException extends HarperException {
    //CHECKSTYLE.OFF: MissingJavadocMethod
    public HarperInvalidEventException() {
        super("Please follow the format: \"event [description] /from [start time] /to [end time]\"\n"
                + "to add a event task into your list.\n"
                + "All fields are required!\n"
                + "Only the whitespace after \"event\" is required.");
    }
}
