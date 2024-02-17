package Commands;

import Tasks.TaskList;

public class ListCommand extends Command {

    static final String COMMAND_FORMAT = "list";
    private final TaskList tasklist;

    public ListCommand(TaskList tasklist) {
        super("list", "list");
        this.tasklist = tasklist;
    }

    public String execute() {
        return tasklist.toString();
    }

    @Override
    public CommandTypes getCommandType() {
        return CommandTypes.LIST;
    }
}
