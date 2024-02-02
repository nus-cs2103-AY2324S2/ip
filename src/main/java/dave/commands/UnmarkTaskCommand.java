package dave.commands;

import dave.Storage;
import dave.TaskList;
import dave.Ui;

public class UnmarkTaskCommand extends Command {
    private int taskNumber;

    public UnmarkTaskCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.getTask(taskNumber).isNotCompleted();
        storage.rewriteOutput(taskList);
        ui.showTaskUnmarked(taskList.getTask(taskNumber));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
