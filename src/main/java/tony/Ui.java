package tony;

/**
 * The Ui class handles user interface-related operations.
 */
public class Ui {

    /**
     * Displays a greeting message when the program starts.
     */
    public static void greeting() {
        String greeting = "_______________________\n"
                + "what is up dawg! I'm tony.Tony!\n"
                + "What can I do for you mate?\n"
                + "_________________________\n";
        System.out.println(greeting);
    }
    /**
     * Displays a goodbye message and exits the program.
     */
    public static void goodbye() {
        String goodbye = "See ya later dawg!\n";
        line();
        System.out.println(goodbye);
        line();
        System.exit(0);
    }
    /**
     * Displays a horizontal line as a separator.
     */
    public static void line() {
        System.out.println("_______________________\n");
    }
}

