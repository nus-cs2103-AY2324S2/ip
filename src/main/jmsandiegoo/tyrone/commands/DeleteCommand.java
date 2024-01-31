package jmsandiegoo.tyrone.commands;

import jmsandiegoo.tyrone.common.Messages;
import jmsandiegoo.tyrone.task.Task;
import jmsandiegoo.tyrone.exceptions.CommandExecutionException;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public CommandResult execute() throws CommandExecutionException {
        try {
            Task delItem = super.taskList.deleteItem(this.index);
            return new CommandResult(
                    String.format(Messages.MESSAGE_DELETE, delItem, super.taskList.getListSize()));
        } catch (IndexOutOfBoundsException e) {
            throw new CommandExecutionException(String.format(Messages.MESSAGE_INCORRECT_COMMAND_INDEX, "delete"));
        }
    }
}
