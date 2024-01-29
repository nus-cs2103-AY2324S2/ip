package duke.Command;

import duke.*;
import duke.Tasks.Task;
import duke.Tasks.TaskList;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int taskIndex) {
        this.index = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index < 1 || index > tasks.size()) {
            throw new DukeException("OOPS!!! duke.Tasks.Task index is out of range.");
        }

        Task deletedTask = tasks.get(index - 1);
        int count = tasks.size() - 1;
        tasks.delete(index - 1);
        Ui.showDeletedMessage(deletedTask, count);
        storage.save(tasks.getAllTasks());

    }

    @Override
    public boolean isExit() {
        return false;
    }
}

