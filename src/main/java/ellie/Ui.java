package ellie;

/**
 * The Ui class handles user interface-related functionalities, such as printing welcome and farewell messages.
 * It also contains constants for a horizontal line and the Ellie logo.
 */
public class Ui {

    static final String HORIZONTAL_LINE = "____________________________________________________";
    static final String HELLO_MESSAGE = "Hello! I'm Ellie, your personal chat bot! \n"
            + "You can call me Stressed Out by a Mountain of Books! \n \n"
            + "I help by tracking your tasks!\n"
            + "What can I do for you? \n \n"
            + "Type 'help' to see available commands! \n";

    static final String GOODBYE_MESSAGE = "\n Bye! Don't be too stressed out!"
            + "\n" + HORIZONTAL_LINE;

    /**
     * Constructs a Ui object.
     */
    public Ui() {

    }

    /**
     * Prints a welcome message.
     */
    public static String showHelloMessage() {
        return HELLO_MESSAGE;
    }

    /**
     * Prints a farewell message and a horizontal line.
     */
    public static String showGoodbyeMessage() {
        return GOODBYE_MESSAGE;
    }


}
