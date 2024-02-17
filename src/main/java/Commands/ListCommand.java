package Commands;

import Tasks.TaskList;
import Utils.CommandTypes;

public class ListCommand extends Command {

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
