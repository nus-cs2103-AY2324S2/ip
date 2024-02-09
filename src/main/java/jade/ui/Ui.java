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
    public static final String LAUNCH_MESSAGE = String.format("%s\nHello, I'm Jade, your task manager.\n\n"
            + "Feel free to set reminders for your task by entering text using the following format:\n\n"
            + "1. todo {Task Description} e.g. todo read a book\n"
            + "2. deadline {Task Description} /by {yyyy-mm-dd hmma} e.g. deadline read a book /by 2024-12-31 300pm\n"
            + "3. event {Task Description} /from {yyyy-mm-dd hmma} /to {yyyy-mm-dd hmma} "
            + "e.g. read a book /from 2024-12-30 200pm /to 2024-12-31 1000pm\n"
            + "\nIf you want to check all tasks on a specific day, use list {yyyy-mm-dd} "
            + "e.g. list 2024-12-30", LOGO);
    public static final String LOADING_ERROR_MESSAGE = "\t[There's no file under current "
             + "storage path, a new task file has been created.]";
}
