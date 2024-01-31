package capone.commands;

import capone.TaskList;
import capone.TaskStorage;
import capone.Ui;

public class ListCommand extends Command{
    @Override
    public void execute(TaskList taskList, Ui ui, TaskStorage storage) {
        for (int i = 0; i < taskList.getSize(); i++) {
            ui.sendMessage(String.format("%d. %s\n", i+1, taskList.getTask(i).toString()));
        }
    }
}
