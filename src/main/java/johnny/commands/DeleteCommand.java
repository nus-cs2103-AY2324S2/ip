package johnny.commands;

import johnny.exceptions.JohnnyException;
import johnny.storage.Storage;
import johnny.tasks.Task;
import johnny.tasks.TaskList;
import johnny.ui.Ui;

public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JohnnyException {
        Task task = tasks.delete(index);
        storage.rewriteFile(tasks);
        ui.showDelete(task, tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
