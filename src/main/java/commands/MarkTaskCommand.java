package commands;

import dave.Storage;
import dave.TaskList;
import dave.Ui;

public class MarkTaskCommand extends Command {
    private int taskNumber;

    public MarkTaskCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.getTask(taskNumber).isCompleted();
        storage.rewriteOutput(taskList);
        ui.showTaskMarked(taskList.getTask(taskNumber));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
