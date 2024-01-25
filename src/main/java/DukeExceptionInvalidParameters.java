/**
 * Represents a Invalid Parameters Exception for our Duke bot.
 * <p>
 * This exception should be thrown specifically for the commands: todo, events and deadlines.
 * It occurs when the parameters provided do not fit the requirement of the command.
 */
public class DukeExceptionInvalidParameters extends DukeException {
    
    /**
     * Creates a DukeExceptionInvalidParameters object.
     * Will call the super constructor from the DukeException object.
     *
     * @param m The error message of the Exception.
     */
    public DukeExceptionInvalidParameters(String m) {
        super(m);
    }

    /**
     * Returns a string representation of this DukeExceptionInvalidParameters.
     * Includes a mini-help guide to tell the user the parameters.
     * 
     * @return a string representation of DukeExceptionInvalidParameters.
     */
    @Override
    public String toString() {
        return "Wait a second, your command has invalid parameters! \n" +
        "Please follow the following format for the commands: \n" +
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
