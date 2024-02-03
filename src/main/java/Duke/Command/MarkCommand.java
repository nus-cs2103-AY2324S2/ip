package Duke.Command;

import Duke.Storage;
import Duke.Task.TaskList;
import Duke.Ui;

import java.lang.reflect.Array;
import Duke.Exception.InvalidArgumentException;

public class MarkCommand extends Command {
    int positionToMark;

    public MarkCommand(String description) {
        this.positionToMark = Integer.parseInt(description);
    }

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) throws InvalidArgumentException {
        try {
            taskList.markTask(positionToMark);
            ui.displayMarkedTask(taskList, positionToMark);
            Storage.save(taskList);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidArgumentException("The index is out of range >.<");
        }

    }
}