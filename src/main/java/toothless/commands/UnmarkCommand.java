package toothless.commands;

import toothless.Storage;
import toothless.TaskList;
import toothless.ToothlessException;
import toothless.Ui;
import toothless.tasks.Task;

public class UnmarkCommand extends Command{
    private String detail;

    public UnmarkCommand (String detail){
        this.detail = detail;
    }

    @Override
    public boolean handle(Ui ui, TaskList taskList, Storage storage) throws ToothlessException {
        int taskIndex = super.getTaskIndex(detail);
        if (taskIndex >= taskList.size() || taskIndex < 0 || detail.equals("")){
            throw new ToothlessException("Human trying to unmark nothing ^O^. Silly");
        }

        Task t = taskList.getTask(taskIndex);
        t.markAsNotDone();

        System.out.println("OK, I've marked this task as not done yet:");
        ui.showTask(t, taskIndex);

        return false;
    }
}
