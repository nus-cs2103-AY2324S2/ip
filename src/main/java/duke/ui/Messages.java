package duke.ui;

/**
 * This class contains all messages displayed by the chatbot.
 */
public class Messages {
    public static final String EXIT_MESSAGE =
        "Aw, see you soon! :3\n";

    public static final String DELETE_ERROR_MESSAGE = "Hey, you need to tell me which one"
        + " to delete! Try 'delete <INDEX>'. ~(>_<)\n";

    public static final String DELETE_INVALID_INDEX_ERROR_MESSAGE = "Sigh.. That's not a valid number!"
        + " Try 'delete <NUMBER>'.\n";

    public static final String INDEX_ERROR_MESSAGE = "I can't do that.. Task index %s is out of range! ~(T_T)\n";

    public static final String SUCCESS_DELETE_MESSAGE = "\nDeleted task %d ~(^-^)\n";
}
