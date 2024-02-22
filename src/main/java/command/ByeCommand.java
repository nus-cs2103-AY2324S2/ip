package command;

import task.TaskList;
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
     * @param storage The storage interface.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DookException {
        storage.write(tasks);
        return "Bye :(. Hope to see you again soon! :3";
    }
}
