package jmsandiegoo.tyrone.commands;

import jmsandiegoo.tyrone.common.Messages;
import jmsandiegoo.tyrone.task.ToDo;

/**
 * Represents the command to add a todo task in the list.
 */
public class TodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
    private final ToDo toDoItem;

    /**
     * @param toDoItem - the todo item to be added into the list
     */
    public TodoCommand(ToDo toDoItem) {
        this.toDoItem = toDoItem;
    }

    /**
     * Returns CommandResult after adding todo task into the list.
     *
     * @return CommandResult - the success result of the execution.
     */
    @Override
    public CommandResult execute() {
        super.taskList.addItem(this.toDoItem);
        return new CommandResult(
                String.format(Messages.MESSAGE_ADD_TASK, this.toDoItem, super.taskList.getListSize()));
    }
}
