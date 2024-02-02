package jmsandiegoo.tyrone.commands;

import jmsandiegoo.tyrone.common.Messages;

/**
 * Represents the list command of the application.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    /**
     * Returns CommandResult containing the current list of tasks.
     *
     * @return CommandResult
     */
    public CommandResult execute() {
        return new CommandResult(
                String.format(Messages.MESSAGE_LIST, super.taskList));
    }
}
