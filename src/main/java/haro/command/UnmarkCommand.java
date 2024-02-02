package haro.command;

import haro.exception.InvalidArgsException;
import haro.Storage;
import haro.task.Task;
import haro.TaskList;
import haro.Ui;


public class UnmarkCommand extends Command {
    int taskNumber;
    public UnmarkCommand(int taskNumber) {
        super(false);
        this.taskNumber = taskNumber;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws InvalidArgsException {
        Task currTask = taskList.unmarkTask(this.taskNumber);
        ui.printUnmarkTask(currTask);
    }

}
