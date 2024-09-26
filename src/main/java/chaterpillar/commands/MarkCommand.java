package chaterpillar.commands;

import chaterpillar.exceptions.ChaterpillarException;
import chaterpillar.storage.Storage;
import chaterpillar.tasks.Task;
import chaterpillar.tasks.TaskList;
import chaterpillar.ui.Ui;

/**
 * <code>Command</code> to mark a task at a specified index as completed.
 *
 * @author marclamp
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Constructor for this class
     *
     * @param index the index of the item to be marked.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Marks the item at the specified index.
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
        currTask.mark();

        storage.saveAllToFile(tasks);

        String output = "Nice! I've marked this task as done:\n" + currTask;

        ui.echo(output);
        return output;
    }
}
