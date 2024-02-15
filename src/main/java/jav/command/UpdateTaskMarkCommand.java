package jav.command;

import jav.exception.InvalidParamException;
import jav.manager.StorageManager;
import jav.manager.UiManager;

/**
 * UpdateTaskMarkCommand handles the executing of updating tasks to marked/unmarked.
 */
public class UpdateTaskMarkCommand extends TaskCommand {
    /** Whether the task is going to be marked or unmarked. */
    private boolean isMarking;

    /**
     * Constructs a new UpdateTaskMarkCommand.
     *
     * @param isMarking whether the task is marked or unmarked.
     * @param param the parameter of the command.
     * @return a new UpdateTaskMarkCommand.
     */
    public UpdateTaskMarkCommand(Boolean isMarking, String param) {
        this.isMarking = isMarking;
        this.param = param;
    }

    @Override
    public String execute() throws InvalidParamException {
        // Check if given a -ve index
        if (Integer.parseInt(param) < 1) {
            throw new InvalidParamException("Cannot mark/unmark task, given num is -ve", null);
        }

        // Check if given an index bigger than size of list
        if (!StorageManager.getInstance().updateTask(Integer.parseInt(param) - 1, isMarking)) {
            throw new InvalidParamException("Cannot mark/unmark task, given num is out of scope", null);
        }

        if (isMarking) {
            return UiManager.getInstance().printMarkingTask();
        } else {
            return UiManager.getInstance().printUnmarkingTask();
        }
    }
}
