package podz.commands;

import podz.exceptions.PodzException;
import podz.task.TaskList;
import podz.ui.Ui;

/**
 * Represents a command to be executed in the task manager.
 */
public abstract class Command {
    protected TaskList taskList;

    /**
     * Sets the task list for the command.
     *
     * @param taskList the task list to be set
     */
    public void setTasks(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Executes the command with the provided user interface.
     *
     * @param ui the user interface for interaction
     * @throws PodzException if there is an issue executing the command
     */
    public abstract void execute(Ui ui) throws PodzException;
}