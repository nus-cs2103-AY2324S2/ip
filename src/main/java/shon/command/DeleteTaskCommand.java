package shon.command;

import shon.exception.ParameterException;

import shon.TaskList;
import shon.Ui;

public class DeleteTaskCommand extends Command {
    private int idx;

    public DeleteTaskCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws ParameterException {
        ui.print(tasks.deleteTask(this.idx));
    }
}
