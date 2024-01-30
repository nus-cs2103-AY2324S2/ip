package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {
    public DeleteCommand(String keyword, String parameters) {
        super(keyword, parameters);
    }

    @Override
    public void execute(Storage storage, Ui ui, TaskList taskList) {
        try {
            Task task = taskList.deleteTask(parameters);
            ui.showDeletedTask(task);
            ui.showTaskListStatus(taskList);
        } catch (Exception e) {
            ui.showError(e.getMessage());
        }
    }
}
