package panda.command;
import panda.component.*;
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
