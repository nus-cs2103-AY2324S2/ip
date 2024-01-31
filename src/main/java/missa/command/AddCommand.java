package missa.command;

import missa.TaskList;
import missa.Ui;
import missa.task.Task;

/**
 * Represents add commands for 3 types of tasks.
 */
public class AddCommand extends Command {
    private TaskList tasks;
    private Task task;

    /**
     * Create an add command.
     *
     * @param task Task to be added.
     * @param tasks Task list to store tasks.
     */
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
