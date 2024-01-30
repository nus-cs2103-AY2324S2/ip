package lamball;

public class Ui {
    private static String welcomeMessage = "    lamball\n" +
            "    ____________________________________________________________\n" +
            "     Hello! I'm Lamball, your helpful sheep!\n" +
            "     Whaaat can I do for you?\n" +
            "    ____________________________________________________________\n";
    private static String goodbyeMessage = "    ____________________________________________________________\n" +
            "     See you again!\n" +
            "    ____________________________________________________________\n";

    private static String indent = "    ____________________________________________________________\n";

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
        System.out.println(welcomeMessage);
    }

    /**
     * Prints a goodbye message.
     *
     */
    public void goodbyeMessage() {
        System.out.println(goodbyeMessage);
    }

    /**
     * Formats the error message, then prints it.
     *
     * @param e Exception that was given.
     */
    public void displayError(Exception e) {
        System.out.println(indent + "    " + e.getMessage() + "\n" + indent);
    }

    /**
     * Formats the action done, then prints it.
     *
     * @param msg Action that was performed.
     */
    public static void displayAction(String msg) {
        System.out.println(indent + "    " + msg + "\n" + indent);
    }
}
