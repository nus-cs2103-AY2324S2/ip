package command;

import exception.BuddyException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

public class DeleteCommand extends Command {
    protected int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BuddyException {
        try {
            Task task = tasks.getTask(taskIndex);
            tasks.deleteTask(task);
            ui.showDelete(task, tasks.size());
        } catch (IndexOutOfBoundsException ioobe) {
            throw new BuddyException("Not a valid task number buddy!");
        }
    }


}
