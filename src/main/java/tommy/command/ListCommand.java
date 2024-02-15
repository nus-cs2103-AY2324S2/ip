package tommy.command;

import tommy.Storage;
import tommy.Ui;
import tommy.task.TaskList;

/**
 * Represents the command to list all tasks in the taskList.
 */
public class ListCommand extends Command {

    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) {
        return ui.displayAllTasks(taskList);
    }
}
