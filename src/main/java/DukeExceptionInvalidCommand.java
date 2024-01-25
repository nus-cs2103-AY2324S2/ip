/**
 * Represents a Invalid Command Exception for our Duke bot.
 * <p>
 * This exception should be thrown when we are unable to recognise our user's input, such as an unknown command etc.
 */
public class DukeExceptionInvalidCommand extends DukeException {
    
    /**
     * Creates a DukeExceptionInvalidCommand object.
     * Will call the super constructor from the DukeException object.
     *
     * @param m The error message of the Exception.
     */
    public DukeExceptionInvalidCommand(String m) {
        super(m);
    }

    /**
     * Returns a string representation of this DukeExceptionInvalidCommand.
     * Includes a mini-help guide to tell the user the available commands.
     * 
     * @return a string representation of DukeExceptionInvalidCommand.
     */
    @Override
    public String toString() {
        return "Hey! I don't know that command yet, I only recognize the following commands: \n" +
        "1. list \n" +
        "2. mark <ID number of task in list> \n" +
        "3. unmark <ID number of task in list> \n" +
        "4. delete <ID number of task in list> \n" +
        "5. todo <event name> \n" +
        "6. event <event name> /from <event from date> /to <event to date> \n" +
        "7. deadline <deadline name> /by <deadline date> \n" +
        "8. bye" + super.toString();
    }
}