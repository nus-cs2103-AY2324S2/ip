package cvb.commands;

/**
 * Represents the types of commands available in the ConvoBot application.
 */
public enum CommandType {

    /**
     * Command type to exit the application.
     */
    BYE,

    /**
     * Command type to list tasks.
     */
    LIST,

    /**
     * Command type to mark a task as done.
     */
    MARK,

    /**
     * Command type to unmark a previously marked task.
     */
    UNMARK,

    /**
     * Command type to delete a task.
     */
    DELETE,

    /**
     * Command type to add a simple to-do task.
     */
    TODO,

    /**
     * Command type to add a task with a deadline.
     */
    DEADLINE,

    /**
     * Command type to add an event task.
     */
    EVENT,

    /**
     * Command type to find matching tasks.
     */
    FIND
}
