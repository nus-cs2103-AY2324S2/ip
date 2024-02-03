package Duke.Command;

import Duke.Storage;
import Duke.Task.TaskList;
import Duke.Ui;

public class MarkCommand extends Command {
    int positionToMark;

    public MarkCommand(String description) {
        this.positionToMark = Integer.parseInt(description);
    }

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) {
        taskList.markTask(positionToMark);
        ui.displayMarkedTask(taskList, positionToMark);
        Storage.save(taskList);
    }
}