public class EmptyDescriptionException extends CheckbotException {
    public EmptyDescriptionException() {
        super("Sorry, the description of a task cannot be empty.");
    }
}
