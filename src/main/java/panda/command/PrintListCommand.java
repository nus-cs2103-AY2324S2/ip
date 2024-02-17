package panda.command;
import panda.component.*;
import panda.exception.PandaException;
public class PrintListCommand extends Command {

    public void execute(TaskList tlist) {
        return;
    }

    /**
     * Shows current TaskList on Ui
     * 
     * @param tlist the current TaskList.
     * @param ui the UI to show message.
     * @param cacheFile the cache file. (unused)
     */
    public void execute(TaskList tlist, Ui ui, Storage cacheFile) {
        ui.showList(tlist);
    }

    /**
     * Generate a string of current TaskList
     * 
     * @param tlist the TaskList into which the task is inserted.
     * @param cacheFile the cache file to save changes to.
     * @return the TaskList containing all matches
     * @throws PandaException if an error occurs during execution.
     */
    public String execute(TaskList tlist, Storage cacheFile) throws PandaException {
        assert tlist != null;
        return tlist.toString();
    }

    /**
     * Checks if the command is an exit command.
     * 
     * @return always false, as this command is not an exit command.
     */
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof PrintListCommand;
    }
}
