package jmsandiegoo.tyrone.commands;

import jmsandiegoo.tyrone.common.Messages;
import jmsandiegoo.tyrone.exceptions.CommandExecutionException;

/**
 * Represents the command to unmark an item in the list.
 */
public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    private final int index;

    /**
     * @param index - the target list index of the item to be unmarked.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Returns CommandResult after un-marking a task from the list.
     * If index is out of bounds it throws CommandExecutionException,
     *
     * @return CommandResult - the success result of the execution.
     */
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
