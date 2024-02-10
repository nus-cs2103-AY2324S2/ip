package chaterpillar.commands;

import chaterpillar.exceptions.ChaterpillarException;
import chaterpillar.tasks.Task;
import chaterpillar.tasks.TaskList;
import chaterpillar.ui.Ui;
import chaterpillar.storage.Storage;

/**
 * <code>Command</code> to unmark a task at a specified index as not completed.
 */
public class UnmarkCommand extends Command {
    private final int index;

    /**
     * Constructor for this class
     * @param index the index of the item to be unmarked.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Un-marks the task at the specified index.
     * @param tasks the list of tasks.
     * @param ui object that handles the UI of this application.
     * @param storage object that is used for storage.
     * @throws ChaterpillarException if there is an error writing to file.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws ChaterpillarException {
        if (index > tasks.size()) {
            ui.echo("Sorry! That item does not exist in the list.\n"
                    + "You currently have " + tasks.size() + " tasks in the list.");
        } else {
            Task currTask = tasks.get(index - 1);
            currTask.unmark();

            ui.echo("Ok, I've marked this task as not done yet:");
            ui.echo(currTask.toString());

            storage.saveAllToFile(tasks);
        }
    }
}
