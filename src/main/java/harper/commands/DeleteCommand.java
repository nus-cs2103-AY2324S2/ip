package harper.commands;

import harper.utils.TaskList;
import harper.utils.Ui;
import harper.utils.Storage;

import harper.tasks.Task;

public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand(int taskIndex) {
        super(false);
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task taskDeleted = taskList.deleteTask(taskIndex);
        storage.save(taskList);
        ui.printSuccessfulDelete(taskList, taskDeleted);
    }
}
