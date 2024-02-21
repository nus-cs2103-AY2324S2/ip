package ellie;

/**
 * The Ui class handles user interface-related functionalities, such as printing welcome and farewell messages.
 * It also contains constants for a horizontal line and the Ellie logo.
 */
public class Ui {

    static final String HORIZONTAL_LINE = "____________________________________________________";

    /**
     * Constructs a Ui object.
     */
    public Ui() {

    }

    /**
     * Prints a welcome message, the Ellie logo, and a horizontal line.
     */
    public static String showHelloMessage() {
        String helloMessage = "";
        helloMessage += "Hello! I'm 'Stressed Out by a Mountain of Books' chat bot! \n";
        helloMessage += "I help by tracking your tasks!\n";
        helloMessage += "What can I do for you? \n \n";
        helloMessage += "Type 'help' to see available commands! \n";
        return helloMessage;
    }

    /**
     * Prints a farewell message and a horizontal line.
     */
    public static String showGoodbyeMessage() {
        String goodbyeMessage = "";
        goodbyeMessage += "\n Bye! Don't be too stressed out!";
        goodbyeMessage += "\n" + HORIZONTAL_LINE;
        return goodbyeMessage;
    }


}
