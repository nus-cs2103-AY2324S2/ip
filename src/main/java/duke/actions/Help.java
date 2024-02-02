package duke.actions;

/**
 * A help command that gives the user a list of all possible commands and how to
 * use them.
 * 
 * @author: CHEN WENLONG
 * @version: CS2103T AY23/24 Semester 2
 */
public class Help extends Command {
    /** String of all possible commands. */
    private static final String HELP = "Active commands:x\n" +
            "\nVIEW\n" +
            "list: lists all tasks saved.\n" +
            "help: lists all available commands.\n" +
            "\nEDIT\n" +
            "mark <index>: marks the task at index to be completed.\n" +
            "unmark <index>: marks the task at index to be not completed.\n" +
            "delete <index>: deletes task at index.\n" +
            "\nADD TASK\n" +
            "todo <name>: adds a todo task with its name.\n" +
            "deadline <name> /by <date>: adds a deadline task with name and date in d-M-yy format." +
            "event <name> /from <date> /to <date>: adds an event task with name and date in d-M-yy format." +
            "find <key>: lists all tasks matching given key";

    /**
     * Constructor for Help command.
     */
    public Help() {
    }

    /** Executes by returning all commands. */
    @Override
    public String execute() {
        return HELP;
    }

}
