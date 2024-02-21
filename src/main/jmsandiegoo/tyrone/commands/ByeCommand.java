package jmsandiegoo.tyrone.commands;

import jmsandiegoo.tyrone.common.Messages;

/**
 * Represents the bye command to exit the application.
 */
public class ByeCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    public ByeCommand() {}
    @Override
    public CommandResult execute() {
        return new CommandResult(Messages.MESSAGE_BYE);
    }
}
