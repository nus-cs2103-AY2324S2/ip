package lamball;

/**
 * A class that primarily responsible for chatbot responses.
 *
 * @author ongzhili
 */

public class Ui {
    private static final String WELCOME_MESSAGE = "    lamball\n" +
            "    ____________________________________________________________\n" +
            "     Hello! I'm Lamball, your helpful sheep!\n" +
            "     Whaaat can I do for you?\n" +
            "    ____________________________________________________________\n";
    private static final String GOODBYE_MESSAGE = "    ____________________________________________________________\n" +
            "     See you again!\n" +
            "    ____________________________________________________________\n";

    private static final String INDENT = "    ____________________________________________________________\n";

    /**
     * Constructor for Ui class.
     *
     */
    public Ui() {

    }

    /**
     * Prints a greeting message.
     *
     */
    public void greetingMessage() {
        System.out.println(WELCOME_MESSAGE);
    }

    /**
     * Prints a goodbye message.
     *
     */
    public void goodbyeMessage() {
        System.out.println(GOODBYE_MESSAGE);
    }

    /**
     * Formats the error message, then prints it.
     *
     * @param e Exception that was given.
     */
    public void displayError(Exception e) {
        System.out.println(INDENT + "    " + e.getMessage() + "\n" + INDENT);
    }

    /**
     * Formats the action done, then prints it.
     *
     * @param msg Action that was performed.
     */
    public void displayAction(String msg) {
        System.out.println(INDENT + "    " + msg + "\n" + INDENT);
    }
}
