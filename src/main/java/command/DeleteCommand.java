package command;

import data.Task;
import data.TaskList;
import data.exception.CoDriverException;
import storage.Storage;
import ui.Ui;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws CoDriverException {
        Task task = tasks.get(index);
        tasks.deleteTask(index);
        ui.showDeleteTask(task, tasks.size());
    }
}
