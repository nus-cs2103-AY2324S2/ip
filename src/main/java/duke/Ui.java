package duke;

/**
 * The Ui class is responsible for handling all user interface elements
 * of the application, such as greeting users, displaying messages, and showing errors.
 * It provides methods to print various types of messages to the console.
 */
public class Ui {

    private static final String LINEBREAK =
            "_______________________________________________________________________________";

    /**
     * Displays a greeting message to the user.
     * This includes an ASCII art representation of a snowman and a welcome message.
     */
    public void greet() {
        String snowBoyAscii =
                "      *      \n"
                        + "     ***     \n"
                        + "   *******   \n"
                        + "  *  o o  *  \n"
                        + " *    >    * \n"
                        + " *  \\___/  * \n"
                        + "  *       *  \n"
                        + "   *******   \n"
                        + "     ***     \n"
                        + "      *      ";
        String toPrint = snowBoyAscii + "\n";
        toPrint += " Hello! I'm SnowBoy\n" + " What can I do for you?";
        Ui.beautify(toPrint);
    }

    /**
     * Beautifies and formats a string message for display.
     * This method adds line breaks before and after the message for better readability.
     *
     * @param toPrint The string message to be formatted and displayed.
     */
    public static void beautify(String toPrint) {
        System.out.println(LINEBREAK);
        System.out.println(toPrint);
        System.out.println(LINEBREAK);
    }

    /**
     * Displays a loading error message.
     * This is typically called when the application fails to load an existing task list
     * and needs to create a new one.
     */
    public void showLoadingError() {
        String toPrint = " No existing list detected. Creating new list...";
        Ui.beautify(toPrint);
    }

    /**
     * Displays an exit message to the user.
     * This message is shown when the user decides to exit the application.
     */
    public void exit() {
        String toPrint = " Bye. Hope to see you again soon!";
        Ui.beautify(toPrint);
    }
}
