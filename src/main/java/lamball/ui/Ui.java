package lamball.ui;

/**
 * A class that primarily responsible for chatbot responses.
 *
 * @author ongzhili
 */

public class Ui {
    private static final String TAB_SPACES = "";
    private static final String WELCOME_MESSAGE =
            "Hello! I'm Lamball, your helpful sheep!\n\n"
                    + "Whaaat can I do for you?\n";

    private static final String GOODBYE_MESSAGE =
        "     See you again!\n";

    /**
     * Constructor for Ui class.
     *
     */
    public Ui() {

    }

    /**
     * Prints a greeting message.
     *
     * @return Welcome message, for GUI processing.
     */
    public String greetingMessage() {
        System.out.println(WELCOME_MESSAGE);
        return WELCOME_MESSAGE;
    }

    /**
     * Prints a goodbye message.
     *
     * @return Goodbye message, for GUI processing.
     */
    public String goodbyeMessage() {
        System.out.println(GOODBYE_MESSAGE);
        return GOODBYE_MESSAGE;
    }

    /**
     * Formats the error message, then prints it.
     *
     * @param e Exception that was given.
     * @return Formatted error message for GUI processing.
     */
    public String displayError(Exception e) {
        String returnVal = TAB_SPACES + e.getMessage() + "\n";
        System.out.println(returnVal);
        return returnVal;
    }

    /**
     * Formats the action done, then prints it.
     *
     * @param msg Action that was performed.
     * @return Formatted action for GUI processing.
     */
    public String displayAction(String msg) {
        String returnVal = TAB_SPACES + msg + "\n";
        System.out.println(returnVal);
        return returnVal;
    }



}
