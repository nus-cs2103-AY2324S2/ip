package shon.command.task;

import shon.command.Command;
import shon.task.TaskList;

/**
 * Represents a command that can be executed to carry out an action for tasks.
 */
abstract class TaskCommand extends Command {
    protected TaskList tasks;

    /**
     * Creates a TaskCommand with the given TaskList.
     * @param tasks The TaskList involved with this TaskCommand.
     */
    public TaskCommand(TaskList tasks) {
        this.tasks = tasks;
    }
}
