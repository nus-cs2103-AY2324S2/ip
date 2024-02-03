package missa.command;

import missa.TaskList;
import missa.Ui;

/**
 * Represents list missa.command.
 */
public class ListCommand extends Command {
    private TaskList tasks;

    public ListCommand(TaskList tasks) {
        this.tasks = tasks;
    }

    @Override
    public TaskList execute() {
        return tasks;
    }

    @Override
    public String getReply(Ui ui) {
        return ui.loadingTaskList(tasks.getTasks());
    }

    @Override
    public boolean isBye() {
        return false;
    }
}
