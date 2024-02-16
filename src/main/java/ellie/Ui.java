package ellie;

/**
 * The Ui class handles user interface-related functionalities, such as printing welcome and farewell messages.
 * It also contains constants for a horizontal line and the Ellie logo.
 */
public class Ui {

    final static String HORIZONTAL_LINE = "____________________________________________________";
    final static String LOGO_ELLIE =
            " _______   ___       ___       ___  _______          \n"
                    + "|\\  ___ \\ |\\  \\     |\\  \\     |\\  \\|\\  ___ \\         \n"
                    + "\\ \\   __/|\\ \\  \\    \\ \\  \\    \\ \\  \\ \\   __/|        \n"
                    + " \\ \\  \\_|/_\\ \\  \\    \\ \\  \\    \\ \\  \\ \\  \\_|/__      \n"
                    + "  \\ \\  \\_|\\ \\ \\  \\____\\ \\  \\____\\ \\  \\ \\  \\_|\\ \\     \n"
                    + "   \\ \\_______\\ \\_______\\ \\_______\\ \\__\\ \\_______\\    \n"
                    + "    \\|_______|\\|_______|\\|_______|\\|__|\\|_______|    \n";


    /**
     * Constructs a Ui object.
     */
    public Ui() {

    }

    /**
     * Prints a welcome message, the Ellie logo, and a horizontal line.
     */
    public static String hello() {
        String helloMessage = "";
        helloMessage += "Hello! I'm Ellie, your CS2103T chat bot! I help by tracking your tasks!\n";
        helloMessage += LOGO_ELLIE + "\n" + HORIZONTAL_LINE + "\n";
        helloMessage += "What can I do for you? Type 'help' to see available commands! \n";
        return helloMessage;
    }

    /**
     * Prints a farewell message and a horizontal line.
     */
    public static String goodbye() {
        String goodbyeMessage = "";
        goodbyeMessage += "\n Bye! Hope to see you again soon!";
        goodbyeMessage += "\n" + HORIZONTAL_LINE;
        return goodbyeMessage;
    }


}
