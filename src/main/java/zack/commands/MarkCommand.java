package zack.commands;

import java.io.IOException;

public class MarkCommand extends Command {
    private int index;
    private boolean isDone;

    public MarkCommand(int index, boolean isDone) {
        this.index = index;
        this.isDone = isDone;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ZackException, IOException {
        if (index < 0 || index >= tasks.getSize()) {
            throw new ZackException("Task index is out of range. Please enter a number between 1 and " + tasks.getSize() + ".");
        }
        Task task = tasks.getTask(index);
        if (isDone) {
            task.markAsDone();
        } else {
            task.unmark();
        }
        ui.showMarkedTask(task, isDone);
        storage.save(tasks.getAllTasks());
    }

}
