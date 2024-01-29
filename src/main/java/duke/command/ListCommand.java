/*
 * ListCommand.java
 * This class represents a command to list all tasks in the Duke application.
 * It allows the user to view the current list of tasks.
 */

package duke.command;

import duke.task.Storage;
import duke.task.TaskList;
import duke.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.listTasks(ui.getWriter());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
