package xilef.command;

import xilef.Storage;
import xilef.Ui;
import xilef.task.Task;
import xilef.task.TaskList;

/**
 * Represents a command to find tasks containing a specified keyword.
 */
public class FindCommand extends Command{

    /**
     * The keyword to search for in task descriptions.
     */
    private final String keyword;

    /**
     * Constructs a new {@code FindCommand} with the specified keyword.
     *
     * @param keyword The keyword to search for.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * {@inheritDoc}
     *
     * Finds the tasks associated with the specified keyword from the task list.
     * Displays the tasks associated with the keyword to the user.
     *
     * @return A string showing the tasks that are associated the with keyword.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList list = new TaskList();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.description.contains(this.keyword)) {
                list.add(task);
            }

        }
        return ui.showFind(list);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
