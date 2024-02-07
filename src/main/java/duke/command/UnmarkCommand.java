package duke.command;

import duke.*;
import duke.task.Task;
import duke.task.TaskList;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (tasks.size() <= index) {
            throw new DukeException("There is nothing to be unmarked");
        } else {
            Task task = tasks.get(index);
            task.unmarkDone();
            storage.save(tasks);
            ui.showUnmarked(task);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
