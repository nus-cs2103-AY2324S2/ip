package Duke.Command;

import Duke.Exception.InvalidArgumentException;
import Duke.Storage;
import Duke.Task.TaskList;
import Duke.Ui;

public class UnmarkCommand extends Command {
    int positionToUnmark;

    public UnmarkCommand(String description) {
        this.positionToUnmark = Integer.parseInt(description);
    }

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) throws InvalidArgumentException {
        try {

        taskList.unmarkTask(positionToUnmark);
        ui.displayUnmarkedTask(taskList, positionToUnmark);
        Storage.save(taskList);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidArgumentException("The index is out of range >.<");
        }
    }
}