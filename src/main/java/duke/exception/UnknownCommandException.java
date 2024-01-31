package duke.exception;

/**
 * The class representing the exception thrown when the entered command is not known.
 * */
public class UnknownCommandException extends DukeException {
    public UnknownCommandException() {
        super("I'm sorry, but I don't understand what you mean :(");
    }
}
