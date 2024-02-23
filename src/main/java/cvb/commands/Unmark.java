package cvb.commands;

import cvb.exceptions.ConvoBotException;
import cvb.utils.ResponseConstructor;
import cvb.utils.TaskList;

/**
 * Represents a command to unmark a completed task in the ConvoBot application.
 */
public class Unmark implements Command {

    private final int indexToUnmark;

    /**
     * Constructs an Unmark command with a given index.
     *
     * @param indexToUnmark The index of the task to be unmarked.
     */
    public Unmark(int indexToUnmark) {
        this.indexToUnmark = indexToUnmark;
    }

    /**
     * Executes the Unmark command by unmarking the specified completed task
     * and displaying a confirmation message.
     *
     * @param taskList The task list containing the task to be unmarked.
     * @param rc       The response constructor for constructing messages.
     * @throws ConvoBotException If an exception specific to ConvoBot occurs during command execution.
     */
    @Override
    public void execute(TaskList taskList, ResponseConstructor rc) throws ConvoBotException {
        taskList.mark(indexToUnmark, false);
        rc.addUnmarked(taskList.getTaskString(indexToUnmark));
    }

    /**
     * Checks if the Unmark command is an exit command.
     *
     * @return Always false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
