package commands;

/**
 * Enum representing the various commands available in the application.
 */
public enum CommandType {
    /**
     * command.Command to list all tasks.
     */
    LIST,

    /**
     * command.Command to add a new to-do task.
     */
    TODO,

    /**
     * command.Command to add a new task with a deadline.
     */
    DEADLINE,

    /**
     * command.Command to add a new event task.
     */
    EVENT,

    /**
     * command.Command to mark a task as completed.
     */
    MARK,

    /**
     * command.Command to unmark a completed task.
     */
    UNMARK,

    /**
     * command.Command to delete a task.
     */
    DELETE,

    /**
     * command.Command to terminate the application.
     */
    BYE
}
