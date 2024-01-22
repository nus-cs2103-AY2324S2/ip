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
    LIST("list", "list"),
    /**
     * Mark the task as done
     */
    MARK("mark","mark <index>"),
    /**
     * Mark the task as not done
     */
    UNMARK("unmark", "unmark <index>"),
    /**
     * Adds a to-do task
     */
    ADD_TODO("todo","todo <name>"),
    /**
     * Adds a deadline task
     */
    ADD_DEADLINE("deadline", "deadline <name> /by <date>"),
    /**
     * Adds an event task
     */
    ADD_EVENT("event", "event <name> /from <start_date> /to <end_date>"),
    /**
     * Invalid command
     */
    INVALID;

    /**
     * Stores the usage template for the command.
     */
    public final String usage;

    /**
     * Stores the command name.
     */
    public final String name;

    /**
     * Constructor for command.
     * @param name the name of the command
     * @param usage the usage template for the command
     */
    Command(String name, String usage) {
        this.name = name;
        this.usage = usage;
    }

    /**
     * Constructor for command, which cannot be called.
     */
    Command() {
        this.usage = null;
        this.name = null;
    }
}