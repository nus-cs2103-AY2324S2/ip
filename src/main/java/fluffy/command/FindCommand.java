package fluffy.command;

import fluffy.storage.Storage;
import fluffy.tasklist.TaskList;
import fluffy.ui.Ui;

/**
 * The FindCommand class is a subclass of Command and represents a command to find tasks in the task list.
 * It takes in a keyword to search for in the task list.
 */
public class FindCommand extends Command {

    private String keyword;

    /**
     * Constructs FindCommand object with keyword as a String.
     */
    public FindCommand(String[] commandParts) {
        this.keyword = commandParts[1];
    }

    /**
     * Executes the command to find tasks in the task list.
     * It filters the tasks in the task list based on the keyword and shows the found tasks using a lambda expression.
     *
     * @param tasks TaskList object representing the task list.
     * @param ui Ui object representing the user interface.
     * @param storage object representing the storage of the task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList foundTasks = tasks.filterTasks(task -> task.getDescription().contains(keyword));
        ui.showFoundTasks(foundTasks);
    }

    public boolean isExit() {
        return false;
    }
}
