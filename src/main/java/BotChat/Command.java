package BotChat;

/**
 * Enumeration representing different commands supported by the botChat application.
 */
public enum Command {
    /**
     * Command to exit the application.
     */
    BYE,

    /**
     * Command to list tasks.
     */
    LIST,

    /**
     * Command to mark a task as done.
     */
    MARK,

    /**
     * Command to unmark a task as done.
     */
    UNMARK,

    /**
     * Command to add an event task.
     */
    EVENT,

    /**
     * Command to add a deadline task.
     */
    DEADLINE,

    /**
     * Command to add a tod0 task.
     */
    TODO,

    /**
     * Command to delete a task.
     */
    DELETE,

    /**
     * Command to find a task.
     */
    FIND,
    /**
     * Command to display all possible commands.
     */
    HELP,

    /**
     * Unknown or unsupported command.
     */
    UNKNOWN
}
