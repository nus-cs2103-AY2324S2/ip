package harper.commands;

import harper.tasks.Task;

import harper.utils.TaskList;
import harper.utils.Ui;
import harper.utils.Storage;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        super(false);
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(this.task);
        storage.save(taskList);
        ui.printSuccessfulAdd(taskList, this.task);
    }
}
