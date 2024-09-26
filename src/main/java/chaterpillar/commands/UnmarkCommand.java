package chaterpillar.commands;

import chaterpillar.exceptions.ChaterpillarException;
import chaterpillar.storage.Storage;
import chaterpillar.tasks.Task;
import chaterpillar.tasks.TaskList;
import chaterpillar.ui.Ui;

/**
 * <code>Command</code> to unmark a task at a specified index as not completed.
 *
 * @author marclamp
 */
public class UnmarkCommand extends Command {
    private final int index;

    /**
     * Constructor for this class
     *
     * @param index the index of the item to be unmarked.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Un-marks the task at the specified index.
     *
     * @param tasks the list of tasks.
     * @param ui object that handles the UI of this application.
     * @param storage object that is used for storage.
     * @return reply from the ChatBot.
     * @throws ChaterpillarException if there is an error writing to file.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ChaterpillarException {
        if (index > tasks.size()) {
            throw new ChaterpillarException(
                    "Sorry! That item does not exist in the list.\n"
                    + "You currently have " + tasks.size() + " tasks in the list.");
        }
        Task currTask = tasks.get(index - 1);
        currTask.unmark();
        storage.saveAllToFile(tasks);

        String output =
                "Ok, I've marked this task as not done yet:\n" + currTask;
        ui.echo(output);
        return output;
    }
}
