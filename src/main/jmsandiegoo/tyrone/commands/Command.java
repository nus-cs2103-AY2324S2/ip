package jmsandiegoo.tyrone.commands;

import jmsandiegoo.tyrone.exceptions.CommandExecutionException;
import jmsandiegoo.tyrone.state.UndoState;
import jmsandiegoo.tyrone.task.TaskList;

/**
 * Represents the general application command
 */
public abstract class Command {
    protected TaskList taskList;

    /**
     * Sets the tasklist for the command to work with
     *
     * @param taskList the list for the command to execute its logic with
     * */
    public void initData(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns CommandResult of the executing command.
     * If there is an unexpected error during execution,
     * CommandExecutionException is thrown.
     *
     * @return CommandResult the success result of the execution.
     * @throws CommandExecutionException If an unexpected error occured.
     */
    public abstract CommandResult execute() throws CommandExecutionException;
}
