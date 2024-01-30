package commands;

import exceptions.ConvoBotException;
import utils.TaskList;
import utils.UI;

/**
 * Represents a command to delete a task from the task list.
 */
public class Delete implements Command {

    private final int i;

    /**
     * Constructor for the Delete command.
     *
     * @param i The index of the task to be deleted.
     */
    public Delete(int i) {
        this.i = i;
    }

    /**
     * Executes the Delete command by deleting the task from the task list and displaying a removal message.
     *
     * @param taskList The task list from which the task will be deleted.
     * @param ui       The user interface for displaying messages.
     * @throws ConvoBotException If an exception specific to ConvoBot occurs during command execution.
     */
    public void execute(TaskList taskList, UI ui) throws ConvoBotException {
        taskList.delete(i);
        ui.showRemoved(taskList.getTaskString(i), taskList.size());
    }

    /**
     * Checks if the Delete command is an exit command. Always returns false for Delete commands.
     *
     * @return Always false.
     */
    public boolean isExit() {
        return false;
    }
}
