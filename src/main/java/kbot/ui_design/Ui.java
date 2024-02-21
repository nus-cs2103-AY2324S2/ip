package kbot.ui_design;

/**
 * Manages all messages that should be printed as the UI component.
 * 
 * @author: CHEN WENLONG
 * @version: CS2103T AY23/24 Semester 2
 */
public class Ui {
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

    /** ASCII art of a wolf */
    private static final String WOLF = "               ..:::::..\n" + //
            "             .:::::::::::.\n" + //
            "            :::::::::::::::\n" + //
            "           :::::::::::::::::\n" + //
            "           :::::::_/|:::::::\n" + //
            "            ::::=/_/:::::::\n" + //
            "             `:_/ |::::::'\n" + //
            "          (   /  ,|:::''\n" + //
            "           \\_/^\\/||__\n" + //
            "        _/~  `\"\"~`\"` \\_\n" + //
            "     __/  -'/  `-._ `\\_\\__\n" + //
            "   /-'`  /-'`  `\\   \\  \\-.\\";

    /** ASCII art of a garden */
    private static final String FLOWERS = "                _(_)_                        wWWWw   _\n" +
            "    @@@@       (_)@(_)  vVVVv    _     @@@@  (___) _(_)_\n" +
            "   @@()@@ wWWWw  (_)\\   (___)  _(_)_  @@()@@   Y  (_)@(_)\n" +
            "    @@@@  (___)     `|/   Y   (_)@(_)  @@@@   \\|/   (_)\\\n" +
            "     /      Y       \\|   \\|/   /(_)    \\|      |/      |\n" +
            "  \\ |     \\ |/       | /\\ | / \\|/       |/    \\|      \\|/\n" +
            " \\\\\\|///  \\\\|/// \\\\\\\\|//\\\\|///\\|///  \\\\\\|//  \\\\|//  \\\\\\|///\n" +
            " ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n";

    /**
     * Constructor for messages
     */
    public Ui() {
    }

    /**
     * Get welcome message.
     * 
     * @return Welcome message.
     */
    public String getStartMessage() {
        return START;
    }

    /**
     * Get end of program message.
     * 
     * @return End of program message.
     */
    public String getEndMessage() {
        return END;
    }

    /**
     * A line to separate user inputs and system outputs.
     * 
     * @return A line.
     */
    public String getLine() {
        return LINE;
    }

    /**
     * Design of a wolf.
     * 
     * @return A wolf.
     */
    public String getWolf() {
        return WOLF;
    }

    /**
     * Design of a flower garden.
     * 
     * @return A flower garden.s
     */
    public String getFlower() {
        return FLOWERS;
    }
}
