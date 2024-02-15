package jav.command;

import jav.exception.InvalidParamException;
import jav.manager.StorageManager;
import jav.manager.UiManager;

/**
 * FindTaskCommand handles the executing of finding tasks that match the given keyword
 */
public class FindTaskCommand extends TaskCommand {
    /**
     * Constructs a new FindTaskCommand.
     *
     * @param param the parameter of the command.
     * @return a new FindTaskCommand.
     */
    public FindTaskCommand(String param) {
        this.param = param;
    }

    @Override
    public void execute() throws InvalidParamException {
        if (param != "") {
            UiManager.getInstance().printFindTask(StorageManager.getInstance().findTask(param));
        } else {
            throw new InvalidParamException("Cannot find task", null);
        }
    }
}
