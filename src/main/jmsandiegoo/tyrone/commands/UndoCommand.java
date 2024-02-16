package jmsandiegoo.tyrone.commands;

import jmsandiegoo.tyrone.common.Messages;
import jmsandiegoo.tyrone.exceptions.CommandExecutionException;

/**
 * Represents the undo command itself of the application.
 */
public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";

    @Override
    public CommandResult execute() throws CommandExecutionException {
        return new CommandResult(Messages.MESSAGE_UNDO);
    }
}
