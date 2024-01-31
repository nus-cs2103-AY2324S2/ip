package jmsandiegoo.tyrone.commands;

import jmsandiegoo.tyrone.common.Messages;
import jmsandiegoo.tyrone.exceptions.CommandExecutionException;

public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public CommandResult execute() throws CommandExecutionException {
        try {
            super.taskList.unmarkItemDone(this.index);
            return new CommandResult(
                    String.format(Messages.MESSAGE_UNMARK, super.taskList.getItem(this.index))
            );
        } catch (IndexOutOfBoundsException e) {
            throw new CommandExecutionException(String.format(Messages.MESSAGE_INCORRECT_COMMAND_INDEX, "unmark"));
        }
    }
}
