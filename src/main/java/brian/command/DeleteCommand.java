package brian.command;

import brian.storage.Storage;
import brian.task.Task;
import brian.task.TaskList;
import brian.ui.TextUi;

public class DeleteCommand extends Command {

    private final int id;

    public DeleteCommand(int id) {
        this.id = id;
    }

    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        assert tasks != null;
        assert ui != null;

        Task task = tasks.getTask(id);
        tasks.remove(id);
        ui.removeTask(task, tasks.size());
    }
}
