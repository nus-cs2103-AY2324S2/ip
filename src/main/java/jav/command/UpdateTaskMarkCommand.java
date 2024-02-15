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
    public void execute() throws InvalidParamException {
        if (Integer.parseInt(param) >= 1) {
            if (StorageManager.getInstance().updateTask(Integer.parseInt(param) - 1, isMarking)) {
                if (isMarking) {
                    UiManager.getInstance().printMarkingTask();
                } else {
                    UiManager.getInstance().printUnmarkingTask();
                }
                return;
            } else {
                throw new InvalidParamException("Cannot mark/unmark task, given num is out of scope", null);
            }
        } else {
            throw new InvalidParamException("Cannot mark/unmark task, given num is -ve", null);
        }
    }
}
