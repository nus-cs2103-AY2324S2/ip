package nicky.command;

import nicky.Ui;
import nicky.task.Storage;
import nicky.task.TaskList;

/**
 * Represents a command to list all tasks in the Nicky application.
 * It allows the user to view the current list of tasks.
 */
public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return tasks.listTasks();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
