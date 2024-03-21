package shon.command.task;

import shon.task.TaskList;

/**
 * The abstract class that represents a command to add a task.
 */
abstract class AddTaskCommand extends TaskCommand {
    protected String description;

    /**
     * Creates a new command to add a task.
     *
     * @param tasks The TaskList associated with this command.
     * @param description the description of the task to be added.
     */
    public AddTaskCommand(TaskList tasks, String description) {
        super(tasks);
        this.description = description;
    }
}
