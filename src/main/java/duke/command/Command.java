package duke.command;

/**
 * A collection of Command Enum objects to store available commands and its signatures.
 */
public enum Command {
    TODO("todo", "todo <task_name>"),
    DEADLINE("deadline", "deadline <task_name> /by <due_date>"),
    EVENT("event","event <task_name> /from <start_date> /to <end_date"),
    LIST("list", "list"),
    MARK ("mark", "mark <task_number>"),
    UNMARK("unmark", "unmark <task_number>"),
    DELETE("delete", "delete <task_number>"),
    TERMINATE("bye", "bye");

    public final String commandName; // the command keyword
    public final String commandSignature; // the command format

    /**
     * Create a command with a keyword and its format
     * @param commandName
     * @param commandSignature
     */
    private Command(String commandName, String commandSignature) {
        this.commandName = commandName;
        this.commandSignature = commandSignature;
    }
}
