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
    public String execute(Storage storage, Ui ui, TaskList taskList) {
        try {
            Task task = taskList.deleteTask(parameters);
            String outputString = ui.showDeletedTask(task);
            outputString += ui.showTaskListStatus(taskList);
            return outputString;
        } catch (Exception e) {
            return ui.showError(e.getMessage());
        }
    }
}
