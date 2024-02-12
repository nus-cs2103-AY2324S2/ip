/*
 * ListCommand.java
 * This class represents a command to list all tasks in the Duke application.
 * It allows the user to view the current list of tasks.
 */

package duke.command;

import duke.Ui;
import duke.task.Storage;
import duke.task.TaskList;

/**
 * Represents a command to list all tasks in the Duke application.
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
