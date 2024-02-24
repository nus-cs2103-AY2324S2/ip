package commands;

/**
 * Enum representing the various commands available in the application.
 */
public enum CommandType {
    /**
     * Command to list all tasks.
     */
    LIST,

    /**
     * Command to add a new to-do task.
     */
    TODO,

    /**
     * Command to add a new task with a deadline.
     */
    DEADLINE,

    /**
     * Command to add a new event task.
     */
    EVENT,

    /**
     * Command to mark a task as completed.
     */
    MARK,

    /**
     * Command to unmark a completed task.
     */
    UNMARK,

    /**
     * Command to delete a task.
     */
    DELETE,

    /**
     * Command to find a task by searching for a keyword.
     */
    FIND,

    /**
     * Command to provide users with a quick reference guide on the available commands, their descriptions,
     * and usage examples within the application.
     */
    HELP,
    
    /**
     * Command to exit the application.
     */
    BYE
}
