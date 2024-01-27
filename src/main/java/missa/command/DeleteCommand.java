package missa.command;

import missa.TaskList;
import missa.Ui;
import missa.command.Command;

/**
 * Represents delete commands.
 */
public class DeleteCommand extends Command {
    private int index;
    private TaskList tasks;

    public DeleteCommand(TaskList tasks, int index) {
        this.tasks = tasks;
        this.index = index;
    }

    @Override
    public TaskList execute(Ui ui) {
        tasks.deleteTask(index);
        System.out.println(ui.replyDeleteCommand(tasks.getATask(index), tasks.getSize()));
        return tasks;
    }

    @Override
    public boolean isBye() {
        return false;
    }
}
