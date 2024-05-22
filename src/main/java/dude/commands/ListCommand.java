package dude.commands;

import dude.tasks.TaskList;

/**
 * The ListCommand class represents a command to list all the tasks in the TaskList object.
 */
public class ListCommand extends Command {

    static final String COMMAND_FORMAT = "list";
    private final TaskList tasklist;

    /**
     * Constructor for the ListCommand class. Returns a command object to list all the tasks in the given
     * TaskList object upon
     * execution.
     *
     * @param tasklist The TaskList object to be listed.
     */
    public ListCommand(TaskList tasklist) {
        super("list", "list");

        assert(tasklist != null);

        this.tasklist = tasklist;
    }

    /**
     * Returns a string representation of the list of tasks in the TaskList object.
     *
     * @return The string message from the execution of the command.
     */
    public String execute() {
        return tasklist.toString();
    }

    @Override
    public CommandTypes getCommandType() {
        return CommandTypes.LIST;
    }
}
