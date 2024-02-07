package duke.command;

import duke.*;
import duke.task.Task;
import duke.task.TaskList;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (tasks.size() <= index) {
            throw new DukeException("There is nothing to be deleted");
        } else {
            Task task = tasks.get(index);
            tasks.remove(index);
            storage.save(tasks);
            ui.showDeleted(task);
            ui.showSize(tasks);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
