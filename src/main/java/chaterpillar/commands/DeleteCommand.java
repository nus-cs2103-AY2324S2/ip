package chaterpillar.commands;

import chaterpillar.exceptions.ChaterpillarException;
import chaterpillar.storage.Storage;
import chaterpillar.tasks.Task;
import chaterpillar.tasks.TaskList;
import chaterpillar.ui.Ui;

/**
 * <code>Command</code> to delete the item in the <code>TaskList</code>>
 * object which contains the list of tasks, at the specified index.
 *
 * @author marclamp
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Constructor for this class.
     *
     * @param index the index of the item to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes a task at the specified index.
     *
     * @param tasks <code>TaskList</code> object for the list of tasks.
     * @param ui <code>Ui</code> object that handles the UI of this application.
     * @param storage <code>Storage</code> object that is used for storage.
     * @return reply from the ChatBot.
     * @throws ChaterpillarException if no number detected, or number
     *     specified does not exist in the <code>TaskList</code>.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ChaterpillarException {
        try {
            Task task = tasks.deleteTaskAtIndex(index);
            storage.saveAllToFile(tasks);

            String output =
                    "I have deleted this task for you: \n"
                    + task.toString() + "\n"
                    + "Now you have " + tasks.size() + " tasks in the list.";
            ui.echo(output);
            return output;
        } catch (NumberFormatException e) {
            throw new ChaterpillarException("Sorry, there is no number detected.\n"
                                            + "The correct way to use the command is: delete number");
        } catch (IndexOutOfBoundsException e) {
            throw new ChaterpillarException("Sorry, the item does not exist in the list.\n"
                                            + "The correct way to use the command is: delete number");
        }
    }
}
