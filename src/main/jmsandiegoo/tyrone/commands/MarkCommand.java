package jmsandiegoo.tyrone.commands;

import jmsandiegoo.tyrone.common.Messages;
import jmsandiegoo.tyrone.exceptions.CommandExecutionException;

/**
 * Represents the command to mark items as done.
 */
public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    private final int index;

    /**
     * @param index - the target list index of the item to be marked.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Returns CommandResult after marking a task as done from the list.
     * If index is out of bounds it throws CommandExecutionException.
     *
     * @return CommandResult - the success result of the execution.
     */
    @Override
    public CommandResult execute() throws CommandExecutionException {
        try {
            super.taskList.markItemDone(this.index);
            return new CommandResult(
                    String.format(Messages.MESSAGE_MARK, super.taskList.getItem(this.index)));
        } catch (IndexOutOfBoundsException e) {
            throw new CommandExecutionException(String.format(Messages.MESSAGE_INCORRECT_COMMAND_INDEX, "mark"));
        }
    }
}