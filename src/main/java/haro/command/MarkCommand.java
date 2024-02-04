package haro.command;

import haro.exception.InvalidArgsException;

import haro.Storage;
import haro.task.Task;
import haro.TaskList;
import haro.Ui;

public class MarkCommand extends Command {
    private int taskNumber;
    public MarkCommand(int taskNumber) {
        super(false);
        this.taskNumber = taskNumber;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws InvalidArgsException {
        Task currTask = taskList.markTask(this.taskNumber);
        ui.printMarkTask(currTask);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (o instanceof MarkCommand) {
            MarkCommand oMarkCommand = (MarkCommand) o;

            if (oMarkCommand.taskNumber == this.taskNumber) {
                return true;
            }
        }
        return false;
    }
}
