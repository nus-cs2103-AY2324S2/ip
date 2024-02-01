package duke.command;

import duke.task.Task;
import duke.utility.Storage;
import duke.utility.TaskList;
import duke.utility.Ui;

import java.io.IOException;

public class UnmarkTaskCommand extends Command{

    private int indexToBeUnmarked;

    public UnmarkTaskCommand(int index) {
        this.indexToBeUnmarked = index;
    }
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task unmarkedTask = taskList.unmarkTask(indexToBeUnmarked);
        ui.showUnmarkedTask(unmarkedTask);
        try {
            storage.saveStorage(taskList.getTaskStore());
        } catch (IOException e) {
            ui.showError(e.getMessage());
        }
    }
    public boolean isExit() {
        return false;
    }
}
