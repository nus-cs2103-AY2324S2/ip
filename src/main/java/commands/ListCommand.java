package commands;

import commands.Command;
import services.Storage;
import services.TaskList;
import services.UI;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        taskList.listTasks();
    }
}
