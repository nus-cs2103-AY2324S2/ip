package haro.command;

import haro.Storage;
import haro.task.Task;
import haro.TaskList;
import haro.Ui;

import haro.exception.InvalidArgsException;
public class DeleteCommand extends Command {
    private int taskNumber;
    public DeleteCommand(int taskNumber) {
        super(false);
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws InvalidArgsException {
        Task currTask = taskList.deleteTask(this.taskNumber);
        ui.printDeleteTask(currTask, taskList.getSize());
    }
}
