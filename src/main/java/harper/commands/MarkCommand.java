package harper.commands;

import harper.utils.TaskList;
import harper.utils.Ui;
import harper.utils.Storage;

import harper.tasks.Task;

public class MarkCommand extends Command {
    private int taskIndex;
    private boolean isMarked;

    public MarkCommand(int taskIndex, boolean isMarked) {
        super(false);
        this.taskIndex = taskIndex;
        this.isMarked = isMarked;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.markTask(this.taskIndex, this.isMarked);
        storage.save(tasks);
        ui.printSuccessfulMark(task, this.isMarked);
    }
}
