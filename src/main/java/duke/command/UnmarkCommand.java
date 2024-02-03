package duke.command;

import duke.Storage;
import duke.Ui;

import duke.task.TaskList;

import duke.exception.InvalidArgumentException;

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