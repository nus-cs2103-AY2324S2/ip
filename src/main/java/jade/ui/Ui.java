package jade.ui;

/**
 * Represents a user interface for the <code>Jade</code> object.
 * A <code>Ui</code> saves messages for user feedback.
 */
public class Ui {
    public static final String LOGO = "  ____   ___    ____     ______\n"
            + "  |  |  / _ \\  |  ___ \\ / |____/\n"
            + "  |  | | | | | | |  | | | |____\n"
            + "  |  | | |_| | | |  | | | |____|\n"
            + "|\\|  | | ___ | | |__| | | |____\n"
            + " \\___| |_| |_| |_____/  \\_|____\\\n"; // logo for the program
    public static final String LAUNCH_MESSAGE = String.format("%s\nGreetings from Jade! I'm Jade,"
            + "your esteemed task management companion.\n\n"
            + "To check how I could assist you, access the help page by entering 'help'.", LOGO);
    public static final String LOADING_ERROR_MESSAGE = "\t[There's no file under current "
             + "storage path, a new task file has been created.]\n";
}
