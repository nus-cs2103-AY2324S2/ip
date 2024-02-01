package Command;

import Task.TaskList;
import Dook.Ui;
import Dook.Storage;
import Dook.DookException;

public class ByeCommand extends Command {

    public ByeCommand() {
        this.isExit = true;
    }

    /**
     * Says goodbye.
     *
     * @param tasks The bot Tasklist.
     * @param ui The user interface.
     * @param storage The storage interface.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DookException {
        ui.println("Bye :(. Hope to see you again soon! ≽^- ˕ -^≼");
        storage.write(tasks);
    }
}
