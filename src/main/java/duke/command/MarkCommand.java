package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

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
