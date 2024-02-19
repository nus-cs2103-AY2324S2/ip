package cvb.commands;

import cvb.exceptions.ConvoBotException;
import cvb.utils.ResponseConstructor;
import cvb.utils.TaskList;

/**
 * Represents a command to delete a task from the task list.
 */
public class Delete implements Command {

    private final int indexToDelete;

    /**
     * Constructs a Delete command.
     *
     * @param indexToDelete The index of the task to be deleted.
     */
    public Delete(int indexToDelete) {
        this.indexToDelete = indexToDelete;
    }

    /**
     * Executes the Delete command by deleting the task from the task list and displaying a removal message.
     *
     * @param taskList The task list from which the task will be deleted.
     * @param rc       The response constructor for constructing messages.
     * @throws ConvoBotException If an exception specific to ConvoBot occurs during command execution.
     */
    @Override
    public void execute(TaskList taskList, ResponseConstructor rc) throws ConvoBotException {
        String removedTaskString = taskList.getTaskString(indexToDelete);
        taskList.delete(indexToDelete);
        rc.addRemoved(removedTaskString, taskList.size());
    }

    /**
     * Checks if the Delete command is an exit command.
     *
     * @return Always false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
