package cvb.commands;

import cvb.exceptions.ConvoBotException;
import cvb.utils.ResponseConstructor;
import cvb.utils.TaskList;

/**
 * Represents a command to mark a task as completed in the ConvoBot application.
 */
public class Mark implements Command {

    private final int indexToMark;

    /**
     * Constructs a Mark command with a given index.
     *
     * @param indexToMark The index of the task to be marked as completed.
     */
    public Mark(int indexToMark) {
        this.indexToMark = indexToMark;
    }

    /**
     * Executes the Mark command by marking the specified task as completed and displaying a confirmation message.
     *
     * @param taskList The task list containing the task to be marked.
     * @param rc       The response constructor for constructing messages.
     * @throws ConvoBotException If an exception specific to ConvoBot occurs during command execution.
     */
    @Override
    public void execute(TaskList taskList, ResponseConstructor rc) throws ConvoBotException {
        taskList.mark(indexToMark, true);
        rc.addMarked(taskList.getTaskString(indexToMark));
    }

    /**
     * Checks if the Mark command is an exit command.
     *
     * @return Always false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
