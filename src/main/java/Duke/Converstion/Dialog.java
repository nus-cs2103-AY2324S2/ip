package Duke.Converstion;

/**
 * Utility class for generating standard dialog messages.
 */
public class Dialog {

    /**
     * Generates a greeting message for the user.
     *
     * @return A string containing a formatted greeting message.
     */
    public static String greetUser() {
        String output = "\t______________________________________________________";
        output += "\tHello! \n\tWhat can I do for you?";
        output += "\t______________________________________________________" + "\n\n";
        return output;
    }

    /**
     * Generates a farewell message for the user.
     *
     * @return A string containing a formatted farewell message.
     */
    public static String fairwellUser() {
        String output = "\t______________________________________________________";
        output += "\tLater";
        output += "\t______________________________________________________" + "\n\n";
        return output;
    }

    /**
     * Generates a standard line separator for formatting purposes.
     *
     * @return A string containing a standard line separator.
     */
    public static String printLine() {
        return "\t______________________________________________________" + "\n";
    }
}
