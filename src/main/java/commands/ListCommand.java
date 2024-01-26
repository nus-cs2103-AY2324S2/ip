package commands;

import commands.Command;
import services.Storage;
import services.TaskList;
import services.UI;

/**
 * List task list command.
 */
public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        taskList.listTasks();
    }
}
