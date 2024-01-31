package toothless.commands;

import toothless.Storage;
import toothless.TaskList;
import toothless.ToothlessException;
import toothless.Ui;
import toothless.tasks.Todo;
import toothless.tasks.Task;

public class TodoCommand extends Command{
    private String detail;

    public TodoCommand (String detail){
        this.detail = detail;
    }
    @Override
    public boolean handle(Ui ui, TaskList taskList, Storage storage) throws ToothlessException {
        if (detail.equals("")) {
            throw new ToothlessException("Human task no name :(");
        }

        Task t = new Todo(detail);
        taskList.addTask(t);

        System.out.println("Got it. I've added this task:");
        ui.showTask(t, taskList.size() - 1);
        System.out.format("Now you have %d tasks in the list.\n", taskList.size());

        return false;
    }
}
