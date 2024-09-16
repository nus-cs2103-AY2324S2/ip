package harper.exceptions;

/**
 * Exception that indicates an invalid update command.
 */
public class HarperInvalidUpdateException extends HarperException {
    //CHECKSTYLE.OFF: MissingJavadocMethod
    public HarperInvalidUpdateException() {
        super("Please make sure that you have followed the following format:\n"
                + "1. update [index] /description [description], for: todo, deadline and event\n"
                + "2. update [index] /by [deadline], for: deadline\n"
                + "3. update [index] /from [start date time], for: event\n"
                + "4. update [index] /to [end date time], for: event\n"
                + "Each update command can only update one field!\n"
                + "It might not work as expected if you put more than"
                + "one field within an update command. So, please don't do that!");
    }
}
