package dave.commands;

import dave.Storage;
import dave.TaskList;
import dave.Ui;
import dave.exceptions.UnableToFindTaskException;

public class MarkTaskCommand extends Command {
    private int taskNumber;

    public MarkTaskCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.getTask(taskNumber).isCompleted();
            storage.rewriteOutput(taskList);
            ui.showTaskMarked(taskList.getTask(taskNumber));
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
