package tony;

/**
 * The Ui class handles user interface-related operations.
 */
public class Ui {

    /**
     * Displays a greeting message when the program starts.
     */
    public static String greeting() {
        String greeting = "_______________________\n"
                + "what is up dawg! I'm Tony!\n"
                + "What can I do for you mate?\n"
                + "_________________________\n";
        return greeting;
    }
    /**
     * Displays a goodbye message and exits the program.
     */
    public static String goodbye() {
        String goodbye = "_______________________\n"
                + "See ya later dawg!\n"
                + "_______________________\n";
        return goodbye;
    }

    /**
     * Displays a message when user says hello
     */
    public static String hello() {
        String goodbye = "_______________________\n"
                + "Hey there dawg! What can I do for ya?\n"
                + "_______________________\n";
        return goodbye;
    }
}

