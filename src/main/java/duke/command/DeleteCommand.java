package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (this.index <= tasks.getTaskSize() && this.index > 0) {
            ui.showDeleteMsg(tasks.getTasks().get(this.index - 1), tasks.getTaskSize());
            tasks.delete(this.index);
            storage.save(tasks);
        } else {
            throw new DukeException("Invalid index."
                    + "Please provide a valid index within the range 1 to "
                    + tasks.getTaskSize() + ".");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
