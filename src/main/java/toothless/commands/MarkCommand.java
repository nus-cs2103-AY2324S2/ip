package toothless.commands;

import toothless.Storage;
import toothless.TaskList;
import toothless.ToothlessException;
import toothless.Ui;
import toothless.tasks.Task;

public class MarkCommand extends Command{

    private String detail;

    public MarkCommand (String detail){
        this.detail = detail;
    }

    @Override
    public boolean handle(Ui ui, TaskList taskList, Storage storage) throws ToothlessException {
        int taskIndex = super.getTaskIndex(detail);
        if (taskIndex >= taskList.size() || taskIndex < 0){
            throw new ToothlessException("Human trying to mark nothing ^O^. Foolish");
        }

        Task t = taskList.getTask(taskIndex);
        t.markAsDone();

        System.out.println("Nice! I've marked this task as done:");
        ui.showTask(t, taskIndex);

        return false;
    }
}
