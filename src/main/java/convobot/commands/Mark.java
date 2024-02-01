package commands;

import exceptions.ConvoBotException;
import utils.TaskList;
import utils.UI;

/**
 * Represents a command to mark a task as completed in the ConvoBot application.
 */
public class Mark implements Command {

    private final int i;

    /**
     * Constructor for the Mark command.
     *
     * @param i The index of the task to be marked as completed.
     */
    public Mark(int i) {
        this.i = i;
    }

    /**
     * Executes the Mark command by marking the specified task as completed and displaying a confirmation message.
     *
     * @param taskList The task list containing the task to be marked.
     * @param ui       The user interface for displaying messages.
     * @throws ConvoBotException If an exception specific to ConvoBot occurs during command execution.
     */
    public void execute(TaskList taskList, UI ui) throws ConvoBotException {
        taskList.mark(i, true);
        ui.showMarked(taskList.getTaskString(i));
    }

    /**
     * Checks if the Mark command is an exit command. Always returns false for Mark commands.
     *
     * @return Always false.
     */
    public boolean isExit() {
        return false;
    }
}
