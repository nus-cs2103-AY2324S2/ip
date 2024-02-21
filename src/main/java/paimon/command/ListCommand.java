package paimon.command;

import paimon.storage.Storage;
import paimon.task.TaskList;
import paimon.ui.Ui;

public class ListCommand extends Command {
    public ListCommand(String keyword, String parameters) {
        super(keyword, parameters);
    }

    @Override
    public String execute(Storage storage, Ui ui, TaskList taskList) {
        return ui.showTaskList(taskList);
    }
}
