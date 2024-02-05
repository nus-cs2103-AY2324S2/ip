package seedu.duke;

/**
 * <h1> DukeException </h1>
 * This DukeException class is a subclass of Exception and creates exceptions that
 * are tailored to the design of the chat bot user interface, adding lines to the error message
 * accordingly.
 *
 * @author Yap Xuan Xuan
 * @version 0.1
 */
public class DukeException extends Exception{
    public DukeException(String tasktype) {
        super("      _________________________________________________________________________n"
                + "      Oops! Please state the description of your " + tasktype
                + "\n      _________________________________________________________________________\n");
    }

    public DukeException() {
        super("      _________________________________________________________________________\n"
                + "      Oops! I'm afraid I don't understand what that means.\n"
                + "      Try adding a todo task like this: todo buy strawberry shortcake\n"
                + "      _________________________________________________________________________\n");
    }

    public DukeException(String error, boolean isDiff) {
        super("      _________________________________________________________________________\n"
                + "      "  + error
                + "\n      _________________________________________________________________________\n");
    }

}
