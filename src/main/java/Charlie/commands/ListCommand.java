package Charlie.commands;

import Charlie.storage.Storage;
import Charlie.storage.TaskList;
import Charlie.ui.Ui;

public class ListCommand extends Command{
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printOutput("Here are the tasks in your list:");
        for (int i = 0; i < taskList.getTasks().size(); i++) {
            ui.printOutput((i + 1) + "." + taskList.getTasks().get(i));
        }
        isExit = false;
    }
}