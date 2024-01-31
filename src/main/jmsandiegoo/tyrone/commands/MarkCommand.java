package jmsandiegoo.tyrone.commands;

import jmsandiegoo.tyrone.common.Messages;
import jmsandiegoo.tyrone.exceptions.CommandExecutionException;

public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

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