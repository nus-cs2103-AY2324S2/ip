package jmsandiegoo.tyrone.commands;

import jmsandiegoo.tyrone.data.TaskList;
import jmsandiegoo.tyrone.exceptions.CommandExecutionException;

public abstract class Command {
    protected TaskList taskList;

    public void initData(TaskList taskList) {
        this.taskList = taskList;
    }

    public abstract CommandResult execute() throws CommandExecutionException;
}