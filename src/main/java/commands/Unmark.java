package commands;

import exceptions.ConvoBotException;
import utils.TaskList;
import utils.UI;

/**
 * Represents a command to unmark a completed task in the ConvoBot application.
 */
public class Unmark implements Command {

    private final int i;

    /**
     * Constructor for the Unmark command.
     *
     * @param i The index of the task to be unmarked.
     */
    public Unmark(int i) {
        this.i = i;
    }

    /**
     * Executes the Unmark command by unmarking the specified completed task and displaying a confirmation message.
     *
     * @param taskList The task list containing the task to be unmarked.
     * @param ui       The user interface for displaying messages.
     * @throws ConvoBotException If an exception specific to ConvoBot occurs during command execution.
     */
    public void execute(TaskList taskList, UI ui) throws ConvoBotException {
        taskList.mark(i, false);
        ui.showUnmarked(taskList.getTaskString(i));
    }

    /**
     * Checks if the Unmark command is an exit command. Always returns false for Unmark commands.
     *
     * @return Always false.
     */
    public boolean isExit() {
        return false;
    }
}
