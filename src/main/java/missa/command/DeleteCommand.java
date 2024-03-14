package missa.command;

import missa.TaskList;
import missa.Ui;
import missa.task.Task;

/**
 * Represents delete commands.
 */
public class DeleteCommand extends Command {
    private int index;
    private TaskList tasks;
    private Task removedTask;

    /**
     * Create a delete command.
     *
     * @param tasks Task list to delete from.
     * @param index Index of task to be deleted.
     */
    public DeleteCommand(TaskList tasks, int index) {
        this.tasks = tasks;
        this.index = index;
        removedTask = tasks.getATask(index);
    }

    @Override
    public TaskList execute() {
        tasks.deleteTask(index);
        return tasks;
    }

    @Override
    public String getReply(Ui ui) {
        return ui.replyDeleteCommand(removedTask, tasks.getSize());
    }

    @Override
    public boolean isBye() {
        return false;
    }
}
