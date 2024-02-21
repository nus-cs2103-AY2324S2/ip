package jav.command;

import jav.exception.InvalidParamException;
import jav.manager.CommandHistoryManager;

/**
 * UndoCommand handles the executing of undoing the most recently executed command.
 */
public class UndoCommand extends TaskCommand {
    @Override
    public String execute() throws InvalidParamException {
        String s = null;
        while (s == null) {
            Command command = CommandHistoryManager.getInstance().popCommandToHistory();
            if (command == null) {
                throw new InvalidParamException("Cannot undo, no more commands in the history", null);
            }

            try {
                s = command.undo();
            } catch (Exception e) {
                throw new InvalidParamException(e.getMessage(), e);
            }
        }

        return s;
    }

    @Override
    public String undo() throws Exception {
        return null;
    }
}
