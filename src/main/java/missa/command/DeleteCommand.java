package missa.command;

import missa.TaskList;
import missa.Ui;
import missa.command.Command;
import missa.task.Task;

/**
 * Represents delete commands.
 */
public class DeleteCommand extends Command {
    private int index;
    private TaskList tasks;
    private Task removedTask;

    public DeleteCommand(TaskList tasks, int index) {
        this.tasks = tasks;
        this.index = index;
        removedTask = tasks.getATask(index);
    }

    @Override
    public TaskList execute(Ui ui) {
        tasks.deleteTask(index);
        System.out.println(ui.replyDeleteCommand(removedTask, tasks.getSize()));
        return tasks;
    }

    @Override
    public boolean isBye() {
        return false;
    }
}
