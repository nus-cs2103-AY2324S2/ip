package missa.command;

import missa.task.Task;
import missa.TaskList;
import missa.Ui;

/**
 * Represents add commands for 3 types of tasks.
 */
public class AddCommand extends Command {
    private TaskList tasks;
    private Task task;

    public AddCommand(Task task, TaskList tasks) {
        this.tasks = tasks;
        this.task = task;
    }

    @Override
    public TaskList execute(Ui ui) {
        tasks.addTask(this.task);
        System.out.println(ui.replyAddCommand(task, tasks.getSize()));
        return tasks;
    }

    @Override
    public boolean isBye() {
        return false;
    }
}
