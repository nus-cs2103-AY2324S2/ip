package ellie;

/**
 * The Ui class handles user interface-related functionalities, such as printing welcome and farewell messages.
 * It also contains constants for a horizontal line and the Ellie logo.
 */
public class Ui {

    final static String horizontalLine = "____________________________________________________________";
    final static String logoEllie =
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
     * Also provides a prompt to enter commands.
     */
    public void hello() {
        System.out.println("Hello! I'm Ellie, your CS2103T chat bot! I help by tracking your tasks!");
        System.out.println(logoEllie + "\n" + horizontalLine);
        System.out.println("What can I do for you? Type 'help' to see available commands! \n");
    }

    /**
     * Prints a farewell message and a horizontal line.
     */
    public void goodbye() {
        System.out.println("\n Bye! Hope to see you again soon!");
        System.out.println(horizontalLine);
    }


}
