package missa.command;

import missa.TaskList;
import missa.Ui;
import missa.command.Command;

/**
 * Represents list missa.command.
 */
public class ListCommand extends Command {
    private TaskList tasks;

    public ListCommand(TaskList tasks) {
        this.tasks = tasks;
    }

    @Override
    public TaskList execute(Ui ui) {
        System.out.println(ui.loadingTaskList(tasks.getTasks()));
        return tasks;
    }

    @Override
    public boolean isBye() {
        return false;
    }
}
