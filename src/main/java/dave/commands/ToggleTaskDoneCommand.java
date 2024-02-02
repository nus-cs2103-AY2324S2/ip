package dave.commands;

import dave.Storage;
import dave.TaskList;
import dave.Ui;

import dave.exceptions.UnableToFindTaskException;

public class ToggleTaskDoneCommand extends Command {
    private int taskNumber;
    private boolean isDone;

    public ToggleTaskDoneCommand(int taskNumber, boolean isDone) {
        this.taskNumber = taskNumber;
        this.isDone = isDone;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.getTask(taskNumber).setDone(this.isDone);
            storage.rewriteOutput(taskList);
            if (this.isDone) {
                ui.showTaskMarked(taskList.getTask(taskNumber));
            } else {
                ui.showTaskUnmarked(taskList.getTask(taskNumber));
            }
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
