package mychats.command;
import mychats.main.Storage;
import mychats.main.TaskList;
import mychats.main.Ui;

/**
 * Represents a command to list the tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the ListCommand by listing the tasks in the task list using Ui.
     *
     * @param tasks TaskList that contains the task list.
     * @param ui Ui that deals with user interactions.
     * @param storage Storage used to load and save tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.displayList(tasks.getTasks());
    }
}
