package chaterpillar.commands;

import chaterpillar.storage.Storage;
import chaterpillar.tasks.Task;
import chaterpillar.tasks.TaskList;
import chaterpillar.ui.Ui;

/**
 * <code>Command</code> to list out the list of tasks given
 * as a parameter in its construction, used in <code>ListAllCommand</code>
 * <code>TasksByDateCommand</code>, and <code>TasksByTodayCommand</code>.
 *
 * @author marclamp
 */
public class ListCommand extends Command {
    private final TaskList tasks;

    /**
     * Constructor for this class.
     *
     * @param tasks list of tasks to be printed.
     */
    public ListCommand(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Prints all the tasks in a specified list of tasks.
     *
     * @param tasks the list of tasks.
     * @param ui object that handles the UI of this application.
     * @param storage object that is used for storage.
     * @return reply from the ChatBot.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder output = new StringBuilder("Here are the tasks in your list: \n");

        int i = 1;
        for (Task eachTask : this.tasks.getTasks()) {
            output.append(i++)
                    .append(". ")
                    .append(eachTask)
                    .append("\n");
        }

        ui.echo(output.toString());
        return output.toString();
    }
}
