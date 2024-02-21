package kbot.actions;

/**
 * A help command that gives the user a list of all possible commands and how to
 * use them.
 * 
 * @author: CHEN WENLONG
 * @version: CS2103T AY23/24 Semester 2
 */
public class Help extends Command {
    /** String of all possible commands. */
    private static final String HELP = "Active commands:\n" +
            "\nVIEWING\n" +
            "list: lists all tasks saved. (Example: list)\n" +
            "help: lists all available commands. (Example: help)\n" +
            "find <String:key>: lists all tasks matching given key. (Example: find homework)" +
            "\nEDITING\n" +
            "mark <int:index>: <index>: marks the task at index to be completed. (Example: mark 1)\n" +
            "unmark <int:index>: marks the task at index to be not completed. (Example: unmark 1)\n" +
            "delete <int:index>: deletes task at index. (Example: delete 1)\n" +
            "\nADD TASK\n" +
            "todo <String:name>: adds a todo task with its name. (Example: todo homework)\n" +
            "deadline <String:name> /by <date>: adds a deadline task with name and date in d-M-yy format. (Example: deadline homework /by 19-2-24)"
            +
            "event <String:name> /from <date> /to <date>: adds an event task with name and date in d-M-yy format. (Example: event career fair /from 20-2-24 /to 22-2-24)";

    /**
     * Constructor for Help command.
     */
    public Help() {
    }

    /**
     * Executes by returning all commands.
     * 
     * @return The String with all commands and parameters required.
     */
    @Override
    public String execute() {
        return HELP;
    }

}
