package Commands;

import Tasks.TaskList;

public class ListCommand extends Command {

    private final TaskList tasklist;

    public ListCommand(TaskList tasklist) {
        super("list", "list");
        this.tasklist = tasklist;
    }

    public String execute() {
        return tasklist.toString();
    }
}
