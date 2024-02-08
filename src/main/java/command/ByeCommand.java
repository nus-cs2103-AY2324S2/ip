package command;

import task.TaskList;
import dook.Ui;
import dook.Storage;
import dook.DookException;

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
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DookException {
        storage.write(tasks);
        return "Bye :(. Hope to see you again soon! ≽^- ˕ -^≼";
    }
}
