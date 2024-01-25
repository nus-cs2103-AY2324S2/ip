
/**
 * Manages all messages that should be printed as the UI component.
 * 
 * @author: CHEN WENLONG
 * @version: CS2103T AY23/24 Semester 2
 */
public class Messages {
    /** A String to separate user input and system output. */
    private static final String LINE = "____________________________________________________________\n";

    /** A String to print when program starts running. */
    private static final String START = "Hey, nice to meet you! I am your personal chatbot:\n\n"
            + "          /'  _/                                /'                           \n"
            + "        /' _/~                                /'                             \n"
            + "     ,/'_/~  ____     O   ____              /'__     ____      ____     ____ \n"
            + "    /\\/~   /'    )  /'  /'    )--/'    /  /'    )  /'    )   )'    )--/'    )\n"
            + "  /'  \\  /'    /' /'  /'    /' /'    /' /'    /' /'    /'  /'       /'    /' \n"
            + "/'     \\(___,/(__(__/(___,/'  (___,/(__(___,/(__(___,/(__/'        (___,/(__ \n"
            + "                  /'             /'                                          \n"
            + "                /'       /     /'                                            \n"
            + "              /'        (___,/'                                              \n\n"
            + "What can I do for you today? :)\n";

    /** A String to print when program stops running. */
    private static final String END = "See you later alligator!\n";

    /**
     * Constructor for messages
     */
    private Messages(){}
    
    /**
     * Get welcome message.
     * @return Welcome message.
     */
    public static String getStartMessage() {
        return START;
    }

    /**
     * Get end of program message.
     * @return End of program message.
     */
    public static String getEndMessage() {
        return END;
    }

    /**
     * A line to separate user inputs and system outputs.
     * @return A line.
     */
    public static String getLine() {
        return LINE;
    }
}
