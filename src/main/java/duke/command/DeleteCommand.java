package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.TextUi;
import duke.utils.DukeException;

public class DeleteCommand extends Command {

    private final int id;

    public DeleteCommand(int id) {
        this.id = id;
    }

    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        Task task = tasks.getTask(id);
        tasks.remove(id);
        ui.removeTask(task, tasks.size());
    }
}
