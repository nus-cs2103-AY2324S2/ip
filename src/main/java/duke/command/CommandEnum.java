package duke.command;

/**
 * A collection of Command Enum objects to store available commands and its signatures.
 */
public enum CommandEnum {
    TODO("todo", "todo <task_name>"),
    DEADLINE("deadline", "deadline <task_name> /by <due_date>"),
    EVENT("event","event <task_name> /from <start_date> /to <end_date"),
    LIST("list", "list"),
    MARK ("mark", "mark <task_number>"),
    UNMARK("unmark", "unmark <task_number>"),
    DELETE("delete", "delete <task_number>"),
    FIND("find", "find <keyword>"),
    TERMINATE("bye", "bye");

    public final String COMMAND_NAME; // the command keyword
    public final String COMMAND_SIGNATURE; // the command format

    /**
     * Create a command with a keyword and its format
     * @param commandName
     * @param commandSignature
     */
    private CommandEnum(String commandName, String commandSignature) {
        this.COMMAND_NAME = commandName;
        this.COMMAND_SIGNATURE = commandSignature;
    }
}
