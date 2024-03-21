package shon.command.task;

import shon.task.TaskList;

/**
 * Represents a command to list the tasks in the <code>TaskList</code>.
 */
public class ListCommand extends TaskCommand {
    /**
     * Creates a ListCommand with the associated tasks.
     *
     * @param tasks The TaskList associated with this command.
     */
    public ListCommand(TaskList tasks) {
        super(tasks);
    }

    /**
     * List the tasks in the tasks <code>TaskList</code> in a user-friendly format.
     *
     */
    @Override
    public String execute() {
        return String.join("\n", this.tasks.getTasks());
    }
}
