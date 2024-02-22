package util;

/**
 * This Message class provides a centralized repository of strings used throughout
 * the application to display various forms of messages.
 */
public class Messages {
    public static final String MESSAGE_NO_SUCH_COMMAND = "Boop Beep Code 404, command not found,"
        + " robot is confused (and sad).";
    public static final String MESSAGE_WRONG_PARAMETERS = "Boop Boop, wrong parameters detected.";
    public static final String MESSAGE_WRONG_PARAMETERS_DEADLINE = "Boop Boop, wrong"
        + "parameters detected for deadline command."
        + "\n Command Format: \n deadline <name of task> /by YYYY-MM-DD HHMM";
    public static final String MESSAGE_SEARCH_NO_RESULTS = "BOOP, BobBot tried his hardest, "
        + "but didn't find anything (and is very sad).";
    public static final String MESSAGE_WRONG_PARAMETERS_EVENT = "Boop Event no boop,"
        + "wrong parameters detected for event command."
        + "\n Command Format: \n event <name of task> /from YYYY-MM-DD HHMM /to YYYY-MM-DD HHMM";
    public static final String MESSAGE_NO_SUCH_ELEMENTS = "Beeeep, the index number you gave does not exists, "
        + "please use an index that exists in list, or else robot will be angry.";
    public static final String MESSAGE_EMPTY_LIST = "Booop, list is empty, you should know this, robot is angry.";
    public static final String MESSAGE_SAVE_CORRUPTED = "Save file has been corrupted, starting anew.";
    public static final String MESSAGE_SAVE_FOUND = "Target Acquired, save file found and loaded.";
    public static final String MESSAGE_END_LINE = "______________________________________";
    public static final String MESSAGE_START_BOT = "Hellooo! I'm \n"
        + "  ____        _     ____        _   \n"
        + " |  _ \\      | |   |  _ \\      | |  \n"
        + " | |_) | ___ | |__ | |_) | ___ | |_ \n"
        + " |  _ < / _ \\| '_ \\|  _ < / _ \\| __|\n"
        + " | |_) | (_) | |_) | |_) | (_) | |_ \n"
        + " |____/ \\___/|_.__/|____/ \\___/ \\__|\n"
        + "As I am a Chatbot, I therefore have no arms.\n"
        + "Knock knock, who's there? Definitely not BobBot!\n"
        + "Jokes aside, what can I do for you?";
    public static final String MESSAGE_END_BOT = "BYE! I'm \n"
        + "  ____        _     ____        _   \n"
        + " |  _ \\      | |   |  _ \\      | |  \n"
        + " | |_) | ___ | |__ | |_) | ___ | |_ \n"
        + " |  _ < / _ \\| '_ \\|  _ < / _ \\| __|\n"
        + " | |_) | (_) | |_) | |_) | (_) | |_ \n"
        + " |____/ \\___/|_.__/|____/ \\___/ \\__|\n"
        + "SEE YOU AROUND!";
}
