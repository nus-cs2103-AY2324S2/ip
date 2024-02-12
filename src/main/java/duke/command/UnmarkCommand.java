package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class UnmarkCommand extends Command {
    public UnmarkCommand(String keyword, String parameters) {
        super(keyword, parameters);
    }

    @Override
    public String execute(Storage storage, Ui ui, TaskList taskList)  {
        try {
            Task task = taskList.markTaskAsUndone(parameters);
            return ui.showUnmarkedTask(task);
        } catch (Exception e) {
            return ui.showError(e.getMessage());
        }
    }
}
