/**
 * Possible ChatBot user commands.
 *
 * @author Titus Chew
 */
public enum Command {
    /**
     * Ends the chat
     */
    BYE,
    /**
     * List the stored text
     */
    LIST,
    /**
     * Mark the task as done
     */
    MARK,
    /**
     * Mark the task as not done
     */
    UNMARK,
    /**
     * Adds a to-do task
     */
    ADD_TODO,
    /**
     * Adds a deadline task
     */
    ADD_DEADLINE,
    /**
     * Adds an event task
     */
    ADD_EVENT,
    /**
     * Invalid command
     */
    INVALID
}