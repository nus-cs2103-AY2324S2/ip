package shodan.command;

/**
 * Represents the commands supported by the chatbot
 */
public enum CommandType {
    /**
     * Exit the chatbot.
     */
    BYE,
    /**
     * List the currently saved tasks.
     */
    LIST,
    /**
     * Mark the selected task as done.
     */
    MARK,
    /**
     * Mark the selected task as not done yet.
     */
    UNMARK,
    /**
     * Add a new Todo task.
     */
    TODO,
    /**
     * Add a new Deadline task.
     */
    DEADLINE,
    /**
     * Add a new Event task.
     */
    EVENT,
    /**
     * Delete the selected task from the list.
     */
    DELETE,
    /**
     * Find all tasks containing the specified keywords.
     */
    FIND
}
