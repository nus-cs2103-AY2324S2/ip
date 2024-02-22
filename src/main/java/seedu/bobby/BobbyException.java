package seedu.bobby;

/**
 * <h1> DukeException </h1>
 * This DukeException class is a subclass of Exception and creates exceptions that
 * are tailored to the design of the chat bot user interface, adding lines to the error message
 * accordingly.
 *
 * @author Yap Xuan Xuan
 * @version 0.1
 */
public class BobbyException extends Exception{

    public BobbyException() {
        super("Oops! I'm afraid I don't understand what that means.\n"
                + "Try adding a todo task like this: todo buy strawberry shortcake\n");
    }

    public BobbyException(String error) {
        super(error);
    }

}
