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
     * Adds a generic task
     */
    ADD,
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
    TODO,
    /**
     * Adds a deadline task
     */
    DEADLINE,
    /**
     * Adds an event task
     */
    EVENT
}