package dave.exceptions;
public class EmptyTaskException extends ChatbotException {
    
    /**
     * Creates new EmptyTaskException.
     * EmptyTaskExceptions are those that occurred from not giving tasks a name after giving the command type.
     * 
     * @param msg The message to show to the user to help them enter the command correctly.
     */
    public EmptyTaskException(String msg) {
        super(msg);
    }
}
