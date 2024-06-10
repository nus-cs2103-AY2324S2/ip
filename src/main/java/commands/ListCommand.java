package commands;

import services.Storage;
import services.TaskList;
import services.UI;

/**
 * List task list command.
 */
public class ListCommand extends AbstractCommand {

    @Override
    public UserCommand execute(TaskList taskList, UI ui, Storage storage) {
        return new UserCommand(taskList.listTasks());
    }
}
