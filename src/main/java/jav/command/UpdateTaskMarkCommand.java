package jav.command;

import jav.exception.InvalidParamException;
import jav.manager.StorageManager;
import jav.manager.UiManager;

/**
 * UpdateTaskMarkCommand handles the executing of updating tasks to marked/unmarked.
 */
public class UpdateTaskMarkCommand extends TaskCommand {
    /** Whether the task is going to be marked or unmarked. */
    private boolean shouldMark;

    /**
     * Constructs a new UpdateTaskMarkCommand.
     *
     * @param shouldMark whether the task should be marked or unmarked.
     * @param param the parameter of the command.
     * @return a new UpdateTaskMarkCommand.
     */
    public UpdateTaskMarkCommand(Boolean shouldMark, String param) {
        this.shouldMark = shouldMark;
        this.param = param;
    }

    @Override
    public String execute() throws InvalidParamException {        
        // Check if given an integer
        int index = 0;
        try {
            index = Integer.parseInt(param);
        } catch (NumberFormatException e) {
            throw new InvalidParamException("Cannot mark/unmark task, given param is not num", null);   
        }

        // Check if given a -ve index
        if (index < 1) {
            throw new InvalidParamException("Cannot mark/unmark task, given num is -ve", null);
        }

        // Check if given an index bigger than size of list
        if (!StorageManager.getInstance().updateTask(index - 1, shouldMark)) {
            throw new InvalidParamException("Cannot mark/unmark task, given num is out of scope", null);
        }

        // Update the task mark based on the parameters
        if (shouldMark) {
            return UiManager.getInstance().printMarkingTask();
        } else {
            return UiManager.getInstance().printUnmarkingTask();
        }
    }
}
