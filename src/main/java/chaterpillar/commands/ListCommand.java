package chaterpillar.commands;

import chaterpillar.tasks.Task;
import chaterpillar.tasks.TaskList;
import chaterpillar.ui.Ui;
import chaterpillar.storage.Storage;

/**
 * <code>Command</code> to list out the list of tasks given
 * as a parameter in its construction, used in <code>ListAllCommand</code>
 * <code>TasksByDateCommand</code>, and <code>TasksByTodayCommand</code>.
 */
public class ListCommand extends Command {
    private final TaskList tasks;

    /**
     * Constructor for this class.
     * @param tasks list of tasks to be printed.
     */
    public ListCommand(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Prints all the tasks in a specified list of tasks.
     * @param tasks the list of tasks.
     * @param ui object that handles the UI of this application.
     * @param storage object that is used for storage.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.echo("Here are the tasks in your list: ");

        int i = 1;
        for (Task eachTask : this.tasks.getTasks()) {
            ui.echo(i++ + ". " + eachTask);
        }
    }
}
