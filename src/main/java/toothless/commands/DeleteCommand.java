package toothless.commands;

import toothless.Storage;
import toothless.TaskList;
import toothless.ToothlessException;
import toothless.Ui;
import toothless.tasks.Task;

public class DeleteCommand extends Command{
    private String detail;

    public DeleteCommand(String detail){
        this.detail = detail;
    }

    @Override
    public boolean handle(Ui ui, TaskList taskList, Storage storage) throws ToothlessException {
        int taskIndex = getTaskIndex(detail);
        if (taskIndex >= taskList.size() || taskIndex < 0 || detail.equals("")){
            throw new ToothlessException("Human trying to delete nothing ^O^. Absurd");
        }

        Task t = taskList.getTask(taskIndex);
        taskList.removeTask(taskIndex);

        System.out.println("Noted. I've removed this task:");
        ui.showTask(t, taskIndex);
        System.out.format("Now you have %d tasks in the list.\n", taskList.size());

        return false;
    }
}
