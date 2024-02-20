package paimon.command;

import paimon.storage.Storage;
import paimon.task.Task;
import paimon.task.TaskList;
import paimon.ui.Ui;

public class MarkCommand extends Command {
    public MarkCommand(String keyword, String parameters) {
        super(keyword, parameters);
    }

    @Override
    public String execute(Storage storage, Ui ui, TaskList taskList) {
        try {
            Task task = taskList.markTaskAsDone(parameters);
            return ui.showMarkedTask(task);
        } catch (Exception e) {
            return ui.showError(e.getMessage());
        }
    }
}
