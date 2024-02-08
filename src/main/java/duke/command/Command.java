package duke.command;

public enum Command {
    TODO("todo", "todo <task_name>"),
    DEADLINE("deadline", "deadline <task_name> /by <due_date>"),
    EVENT("event","event <task_name> /from <start_date> /to <end_date"),
    LIST("list", "list"),
    MARK ("mark", "mark <task_number>"),
    UNMARK("unmark", "unmark <task_number>"),
    DELETE("delete", "delete <task_number>"),
    FIND("find", "find <keyword>"),
    TERMINATE("bye", "bye");

    public final String commandName;
    public final String commandSignature;

    private Command(String commandName, String commandSignature) {
        this.commandName = commandName;
        this.commandSignature = commandSignature;
    }
}
