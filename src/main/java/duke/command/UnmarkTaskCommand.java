package duke.command;

import duke.task.Task;
import duke.utility.DukeException;
import duke.utility.Storage;
import duke.utility.TaskList;
import duke.utility.Ui;

import java.io.IOException;

public class UnmarkTaskCommand extends Command{

    private int indexToBeUnmarked;

    public UnmarkTaskCommand(int index) {
        this.indexToBeUnmarked = index;
    }
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            Task unmarkedTask = taskList.unmarkTask(indexToBeUnmarked);
            ui.showUnmarkedTask(unmarkedTask);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException("*HONK* Pengu thinks you need a valid task number to delete, " +
                    "consider checking the list command");
        }
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
