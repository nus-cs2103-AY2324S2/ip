package capone.commands;

import capone.Storage;
import capone.TaskList;
import capone.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        for (int i = 0; i < taskList.getSize(); i++) {
            ui.sendMessage(String.format("%d. %s\n", i + 1, taskList.getTask(i).toString()));
        }
    }
}
