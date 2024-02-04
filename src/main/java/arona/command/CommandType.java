package arona.command;

/**
 * Commands that can be used.
 */
public enum CommandType {
    /**
     * Command to exit.
     */
    BYE,
    /**
     * Command to create a To Do task.
     */
    TODO,
    /**
     * Command to create a Deadline task.
     */
    DEADLINE,
    /**
     * Command to create an Event task.
     */
    EVENT,
    /**
     * Command to delete a task from the list.
     */
    DELETE,
    /**
     * Command to list out all tasks.
     */
    LIST,
    /**
     * Command to mark a task as done.
     */
    MARK,
    /**
     * Command to mark a task as not done.
     */
    UNMARK,
    FIND,
}
