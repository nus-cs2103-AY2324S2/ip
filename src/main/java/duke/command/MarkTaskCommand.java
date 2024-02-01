package duke.command;

import duke.task.Task;
import duke.utility.Storage;
import duke.utility.TaskList;
import duke.utility.Ui;

import java.io.IOException;

public class MarkTaskCommand extends Command{
    private int indexToBeMarked;

    public MarkTaskCommand(int index) {
        this.indexToBeMarked = index;
    }
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task markedTask = taskList.markTask(indexToBeMarked);
        ui.showMarkedTask(markedTask);
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
