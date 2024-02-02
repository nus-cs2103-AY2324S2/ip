package dave.commands;

import dave.Storage;
import dave.TaskList;
import dave.Ui;
import dave.exceptions.UnableToFindTaskException;

public class UnmarkTaskCommand extends Command {
    private int taskNumber;

    public UnmarkTaskCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.getTask(taskNumber).isNotCompleted();
            storage.rewriteOutput(taskList);
            ui.showTaskUnmarked(taskList.getTask(taskNumber));
        } catch (Exception exc) {
            ui.showHorizontalLine();
            System.out.println(new UnableToFindTaskException(taskList).getMessage());
            ui.showHorizontalLine();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
