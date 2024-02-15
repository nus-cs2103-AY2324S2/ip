package chatbot.action;

import chatbot.action.exception.ActionException;
import chatbot.action.util.Argument;
import chatbot.action.util.Command;
import chatbot.action.util.SuppliedArgument;

/**
 * Represents Actions that modify the state of the task list,
 * except for the undo action.
 *
 * @author Titus Chew
 */
public abstract class ModifyAction extends Action {
    /**
     * Constructor for this action that modifies the task list
     *
     * @param command The {@link Command} associated with this action.
     * @param suppliedArguments The {@link Argument}(s) supplied with the command.
     * @throws ActionException If the action fails has unrecognizable or missing {@link Argument}(s).
     */
    ModifyAction(Command command, SuppliedArgument[] suppliedArguments) throws ActionException {
        super(command, suppliedArguments);
    }
}
