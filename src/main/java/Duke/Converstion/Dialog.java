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
        String output = "";
        output += "Hello! \nWhat can I do for you?";
        return output;
    }

    /**
     * Generates a farewell message for the user.
     *
     * @return A string containing a formatted farewell message.
     */
    public static String fairwellUser() {
        String output = "";
        output += "Later";
        return output;
    }
}
